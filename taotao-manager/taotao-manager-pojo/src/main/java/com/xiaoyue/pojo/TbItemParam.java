package com.xiaoyue.pojo;

import java.util.Date;

public class TbItemParam {
    private Long id;

    private Long itemCatId;

    private Date created;

    private Date updated;

    private String paramData;

    public TbItemParam(Long id, Long itemCatId, Date created, Date updated, String paramData) {
        this.id = id;
        this.itemCatId = itemCatId;
        this.created = created;
        this.updated = updated;
        this.paramData = paramData;
    }

    public TbItemParam() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
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

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TbItemParam{");
        sb.append("id=").append(id);
        sb.append(", itemCatId=").append(itemCatId);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", paramData='").append(paramData).append('\'');
        sb.append('}');
        return sb.toString();
    }
}