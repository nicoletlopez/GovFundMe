package beans;

import models.daos.CreateProjectDao;
import models.services.CreateProjectService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class NavBean implements Serializable
{
    private List<String> categories;

    public NavBean()
    {
        CreateProjectService listCategories = new CreateProjectDao();
        categories = listCategories.getAllCategories();
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
