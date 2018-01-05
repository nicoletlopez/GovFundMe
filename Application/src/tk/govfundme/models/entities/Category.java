package tk.govfundme.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Category implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;

    @Basic
    private String categoryName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryId", cascade = CascadeType.ALL)
    private List<Project> projects;

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public List<Project> getProjects()
    {
        return projects;
    }

    public void setProjects(List<Project> projects)
    {
        this.projects = projects;
    }
}
