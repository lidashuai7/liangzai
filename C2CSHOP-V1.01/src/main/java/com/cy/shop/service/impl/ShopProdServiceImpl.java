package com.cy.shop.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.common.util.Base64ToImage;
import com.cy.shop.common.vo.PageObject;
import com.cy.shop.dao.ShopProdDao;
import com.cy.shop.entity.ShopProd;
import com.cy.shop.service.ShopProdService;

@Service
public class ShopProdServiceImpl implements ShopProdService {
	@Autowired
	private ShopProdDao shopProdDao;

	// 根据id查询一条记录，用在商品详情页
	@Override
	public ShopProd doFindObjectById(Integer prodId) {
		ShopProd result = shopProdDao.findObjectById(prodId);
		if (prodId == null || prodId < 1)
			throw new IllegalArgumentException("商品id不合法");
		if (result == null)
			throw new ServiceException("没有查询到记录");
		return result;
	}

	@Override
	public PageObject<ShopProd> findPageObjects(String name, Integer pageCurrent) {
		// 首先校验pageCurrent
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("当前页码不正确");
		}
		// 查询总记录数
		int rowCount = shopProdDao.getRowCount(name);
		// 校验查找到的数据
		if (rowCount == 0) {
			throw new ServiceException("系统没有查到对应记录");
		}
		// 总7条，大小3，当前第二页,从（3*（2-1））开始查
		Integer pageSize = 3;
		Integer startIndex = pageSize * (pageCurrent - 1);
		List<ShopProd> list = shopProdDao.findPageObjects(name, startIndex, pageSize);
		PageObject<ShopProd> po = new PageObject<>(rowCount, pageCurrent, pageSize, list);
		return po;
	}

	@Override
	public int doSaveObject(ShopProd entity, String file) {
		if (file == null || entity == null || file.length() < 1)
			throw new ServiceException("数据不能为空");
		if (StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("商品名不能为空");

		// 将图片字符串转化为图片保存
		String[] split = file.split(",");
		Base64ToImage toImg = new Base64ToImage();
		String path = ClassUtils.getDefaultClassLoader().getResource("").getPath().replace("target/classes/", "")
				+ "src/main/resources/static/assets/pic/"+ entity.getName() + "/";
		File f=new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}
		StringBuilder photo = new StringBuilder();
		StringBuilder mainPhoto = new StringBuilder();
		System.out.println(path);
		for (int i = 0; i < split.length; i++) {
			if (i % 2 != 0) {
				String imgpath=((i + 1) / 2) + ".jpg";
				toImg.base64ToImage(split[i],path  + imgpath);
				photo.append("../assets/pic/"+entity.getName()+"/"+imgpath+",");
				if(((i + 1) / 2)==1) {
					mainPhoto.append("../assets/pic/"+entity.getName()+"/"+imgpath);
				}
			}
		}
		photo.deleteCharAt(photo.length()-1);
		entity.setBigPhoto(photo.toString());
		entity.setSmallPhoto(photo.toString());
		entity.setMainPhoto(mainPhoto.toString());
		int row = shopProdDao.insertObject(entity);
		if(row==0)
			throw new ServiceException("保存失败");
		return 0;
	}
	//根据id删除记录
	@Override
	public int doDeleteObjects(Integer[] ids) {
		if(ids==null||ids.length==0)
			throw new ServiceException("id不能为空");
		for (Integer id : ids) {
			String path = ClassUtils.getDefaultClassLoader().getResource("").getPath().replace("target/classes/", "")
					+ "src/main/resources/static/assets/pic/"+ shopProdDao.findNameById(id) + "/";
			deletePhotoByName(path);
		}
		int rows=shopProdDao.deleteObjects(ids);
		return rows;
	}
	private boolean deletePhotoByName(String delpath) {
            File file = new File(delpath);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                        System.out.println(delfile.getAbsolutePath() + "删除文件成功");
                    } else if (delfile.isDirectory()) {
                    	deletePhotoByName(delpath + "\\" + filelist[i]);
                    }
                }
                System.out.println(file.getAbsolutePath() + "删除成功");
                file.delete();
            }
        return true;
	}

}
