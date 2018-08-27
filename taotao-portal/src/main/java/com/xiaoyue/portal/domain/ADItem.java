package com.xiaoyue.portal.domain;

public class ADItem {
    private String srcB;
    private Integer widthB;
    private Integer heightB;
    private String alt;
    private String src;
    private Integer width;
    private Integer height;
    private String href;

    public ADItem() {
    }

    public String getSrcB() {
        return srcB;
    }

    public void setSrcB(String srcB) {
        this.srcB = srcB;
    }

    public Integer getWidthB() {
        return widthB;
    }

    public void setWidthB(Integer widthB) {
        this.widthB = widthB;
    }

    public Integer getHeightB() {
        return heightB;
    }

    public void setHeightB(Integer heightB) {
        this.heightB = heightB;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ADItem{");
        sb.append("srcB='").append(srcB).append('\'');
        sb.append(", widthB=").append(widthB);
        sb.append(", heightB=").append(heightB);
        sb.append(", alt='").append(alt).append('\'');
        sb.append(", src='").append(src).append('\'');
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", href='").append(href).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
