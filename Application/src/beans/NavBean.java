package beans;

import models.daos.CategoryDao;
import models.daos.ProjectDao;
import models.services.CategoryService;
import models.services.ProjectService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class NavBean implements Serializable
{
    private List<String> categories;

    public NavBean()
    {
        CategoryService listCategories = new CategoryDao();
        categories = listCategories.getCategories();
    }

    public List<String> getCategories()
    {
        return categories;
    }

    public void setCategories(List<String> categories)
    {
        this.categories = categories;
    }
}
