package bjfu.eight.mall.entity.vo;

import bjfu.eight.mall.entity.po.Params;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class AllParams {
    private int id;
    private int parentId;
    private String name;
    private int sortOrder;
    private int status;
    private int level;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    public List<AllParams> getChildren() {
        return children;
    }

    public void setChildren(List<AllParams> children) {
        this.children = children;
    }

    List<AllParams> children;
}
