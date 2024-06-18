package vn.dev.cinema.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.dev.cinema.entity.Category;
import vn.dev.cinema.model.CategoryModel;
import vn.dev.cinema.model.SticketTypeModel;

@Service
public class CategoryService extends BaseService<Category>{

	@Override
	public Class<Category> clazz() {
		return Category.class;
	}
	
	@Autowired
	private TransmitService transmitService;
	
	
	public CategoryModel getModelById(Integer id) {
		try {
			return transmitService.categoryToModel(super.getById(id));
		}catch(Exception e) {
			return new CategoryModel();
		}
		
	}
	
	public List<CategoryModel> findAllModel(){
		return transmitService.categoryEntitiesToModels(super.findAll());
	}

	@Transactional
	public void saveCategory(CategoryModel model) {
		super.saveOrUpdate(transmitService.categoryToEntity(model));
	}
	
	@Transactional
	public void updateCategory(CategoryModel model, Integer id) {
		model.setId(id);
		model.setUpdateDate(new Date());
		Category entity = transmitService.categoryToEntity(model);
		super.saveOrUpdate(entity);
	}
	
	@Transactional
	public boolean delById(Integer id) {
		super.deleteById(id);
		return true;
	}
}
