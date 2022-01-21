package com.zzh.estore.entity;

/**
 * @author ：zzh
 * @description ：省市区的数据实体类
 * @date ：Created in 2022/1/12 9:53
 */
public class District extends BaseEntity{
    private Integer id;
    private String parent;
    private String code;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        District district = (District) o;

        if (id != null ? !id.equals(district.id) : district.id != null) return false;
        if (parent != null ? !parent.equals(district.parent) : district.parent != null) return false;
        if (code != null ? !code.equals(district.code) : district.code != null) return false;
        return name != null ? name.equals(district.name) : district.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", parent='" + parent + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
