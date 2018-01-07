package tk.govfundme.beans;

import tk.govfundme.models.daos.CategoryDao;
import tk.govfundme.models.daos.ProjectDao;
import tk.govfundme.models.services.CategoryService;
import tk.govfundme.models.services.ProjectService;

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
