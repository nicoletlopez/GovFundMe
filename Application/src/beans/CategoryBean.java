package beans;

import models.daos.CategoryDao;
import models.services.CategoryService;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

@ManagedBean
public class CategoryBean implements Serializable
{
    private List<String> categoryNamesListString;

    public CategoryBean()
    {
        CategoryService listCategories = new CategoryDao();
        categoryNamesListString = listCategories.getCategories();
    }

    public List<String> getCategoryNamesListString()
    {
        return categoryNamesListString;
    }

    public void setCategoryNamesListString(List<String> categoryNamesListString)
    {
        this.categoryNamesListString = categoryNamesListString;
    }
}
