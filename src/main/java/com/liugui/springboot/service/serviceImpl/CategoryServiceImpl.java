package com.liugui.springboot.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liugui.springboot.dao.CategoryMapper;
import com.liugui.springboot.pojo.Category;
import com.liugui.springboot.req.CategoryReq;
import com.liugui.springboot.req.UpdateCategoryReq;
import com.liugui.springboot.service.CategoryService;
import com.liugui.springboot.util.CopyUtil;
import com.liugui.springboot.util.SnowFlake;
import com.liugui.springboot.vo.CategoryVo;
import com.liugui.springboot.vo.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Resource
    private CategoryMapper categoryMapper;


    @Resource
    private SnowFlake snowFlake;

    @Override
    public PageVo bookList(CategoryReq req)
    {
//        List<CategoryVo> categoryVos = new ArrayList<>();
//        for (Category category : categorys) {
//            CategoryVo categoryVo = new CategoryVo();
//            BeanUtils.copyProperties(category,categoryVo);
//            categoryVos.add(categoryVo);
//        }
        List<Category> categorys=null;
        if(req.getPageNum() == 0 && req.getPageSize() == 0) {
            req.setPageNum(1);
            req.setPageSize(5);
        }
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        if(!ObjectUtils.isEmpty(req.getName())){
            categorys = categoryMapper.searchLike(req.getName());
        }else{
            categorys = categoryMapper.searchAllBook();
        }
        PageInfo<Category> pageInfo = new PageInfo<>(categorys);
        logger.info("分页参数为{}",pageInfo);
        List<CategoryVo> categoryVos=CopyUtil.copyList(categorys,CategoryVo.class);
        PageVo<CategoryVo> pageVo = new PageVo<>();
        pageVo.setContent(categoryVos);
        pageVo.setTotal((int)pageInfo.getTotal());
        return pageVo;
    }
    @Override
    public List<CategoryVo> all() {
        List<Category> categorys=null;
        categorys = categoryMapper.searchAllBook();
        List<CategoryVo> categoryVos=CopyUtil.copyList(categorys,CategoryVo.class);
        return categoryVos;
    }

    @Override
    public int updateSelectiveCategory(UpdateCategoryReq updateCategoryReq){
        if(!ObjectUtils.isEmpty(categoryMapper.selectByPrimaryKey(updateCategoryReq.getId()))){
            Category newBook = CopyUtil.copy(updateCategoryReq, Category.class);
            logger.info("更新的书籍id{}-名称{}",newBook.getId(),newBook.getName());
            int i = categoryMapper.updateByPrimaryKeySelective(newBook);
            return i;
        }else{
//            long id = UUID.randomUUID().toString().
            long id = snowFlake.nextId();
            updateCategoryReq.setId(id);
            Category category = CopyUtil.copy(updateCategoryReq, Category.class);
            int i = categoryMapper.insertSelective(category);
            return i;
        }
    }

    @Override
    public int deleteById(Long id) {
        int i = categoryMapper.deleteByPrimaryKey(id);
        return i;
    }
}
