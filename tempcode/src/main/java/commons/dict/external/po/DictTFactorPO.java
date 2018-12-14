package commons.dict.external.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tjhq.commons.dict.util.DictDBConstants;
/**
 * 列 实体
 * @author xujingsi
 *
 */
public class DictTFactorPO implements Serializable,Cloneable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8529073149582514096L;

	private String year;

    private String admdivcode;
    /**
     * 表ID
     */
    private String tableid;
     /**
      * 数据源ID
      */
    private String deid;
    /**
     * 列ID
     */
    private String columnid;
    /**
     * 列名称
     */
    private String name;
    /**
     * 物理列名
     */
    private String dbcolumnname;
    /**
     * 1整型、 2小数、3字符、4标题、6引用、7大文本、8日期
     */
    private Integer datatype;
    /**
     * 数据存储宽度，对于字符列，则为数据库表列宽；对于大文本型，则为可录入数据量
     */
    private Integer datalength;
    /**
     * 小数位数
     */
    private Integer scale;
    /**
     * 是否允许为空，如果该列为主键，则默认不允许为空
     */
    private String nullable;
    /**
     * 默认值，不设置则为NULL
     */
    private String defaultvalue;
    /**
     * 引用ID
     */
    private String csid;
    /**
     * 是否更新
     */
    private String isupdate;
    /**
     * 版本
     */
   // private Integer version;
    /**
     * 排序层次码
     */
    private Integer orderid;
    /**
     * 上级ID
     */
    private String superid;
    /**
     * 末级标志
     */
    private String isleaf;
    /**
     * 级次码（1,2,3,4,5..）
     */
    private Integer levelno;
    /**
     * 中文别名
     */
    private String alias;
    /**
     * 显示掩码格式    显示样式  存储内容
            YYYY    2008    2008-01-01
           YYYY-MM  2008-12    2008-12-01
     */
    private String colformat;
    /**
     * 界面显示处理方式。
                TEXT = "0"
				MASK_TEXT = "1"
				大文本="A"
				---
				Date="2"
				SHORT DATE="7"
				---
				CHECK = "3"
				LIST="4"
				BUTTON="5"
				RADIO = "6"
				---
				POP_WINDOW = "8"
				HTML="9"
				Picture="B"
				File="C"
     */
    private String showformat;
    /**
     * 界面显示宽度，以字节为单位
     */
    private Integer showwidth;
    /**
     * 是否为主键（逻辑）
     */
    private String iskey;
    /**
     * 是否可见
     */
    private String isvisible;
    /**
     * 是否为保留字段
     */
    private String isreserve;
  
	/**
     * 是否需要汇总
     */
    private String issum;
    /**
     * 是否使用正则表达式对输入内容进行验证
     */
    private String isregex;
    /**
     * 正则表达式内容
     */
    private String regexpr;
    /**
     * 正则表达式错误说明
     */
    private String regexprinfo;
    /**
     * 是否绑定列
     */
    private String isbandcol;
    /**
     *绑定引用列的COLUMNCODE 
     */
    private String bandcolumnid;
    
    /**
     * 对应引用表的列
     */
    private String bandrefdwcol;
    /**
     * 扩展属性，参见Dict_t_COLEXTPROP
     */
    private String extprop;
    /**
     * 列的tips说明
     */
    private String coltips;
    /**
     * 对于多表关联，某TABLECODE
     */
    private String frmtabid;
    /**
     * 对于多表关联，某表的COLUMNCODE
     */
    private String frmcolid;
    /**
     * 是否虚列
     */
    private String isvirtual;
    /**
     * 虚列表表达式
     */
    private String vircontext;
    /**
     * 1中央、2省、3市、4县
     */
    private String bgtlvl;
    /**
     * 是否超链接
     */
    private String ishref;
    /**
     * 超链接窗口打开方式
     */
    private String openwindowtype;
	 /**
	  * 超链接地址
	  */
    private String hrefloc;
    /**
     * 超链接参数ID,参见DICT_T_SETHREFPARM
     */
    private String hrefparmid;
    
    /**
     * 引用列中判断是否可以选中中间级节点 0 不可选，1可选
     */
    private String parentNodeCanCheck;
    
    /**
     * 列对应EXCEL列ID
     */
    private String xlsxcolumnid;
    
    private int columnindex;
    private String csdbtablename;
    

    
    //alter table Dict_T_Factor add (isKey char(1) default '0' not null);
    
    /**
     * 实际列 用于 树状结构
     */
    private List<DictTFactorPO> dictTFactorList  = new ArrayList<DictTFactorPO>();

    
    
    private Boolean drag; 
    private Boolean dropInner;
    private Boolean dropRoot;
    private Boolean inner;
    private boolean open;
    private boolean nocheck;
    private String changename;
    private String columnoldid;
    
    
    public String getParentNodeCanCheck() {
    	if(StringUtils.isEmpty(this.parentNodeCanCheck)){
    		return "0";
    	}
		return parentNodeCanCheck;
	}

	public void setParentNodeCanCheck(String parentNodeCanCheck) {
		this.parentNodeCanCheck = parentNodeCanCheck;
	}

	public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAdmdivcode() {
        return admdivcode;
    }

    public void setAdmdivcode(String admdivcode) {
        this.admdivcode = admdivcode;
    }

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    public String getDeid() {
        return deid;
    }

    public void setDeid(String deid) {
        this.deid = deid;
    }

    public String getColumnid() {
        return columnid;
    }

    public void setColumnid(String columnid) {
        this.columnid = columnid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbcolumnname() {
        return dbcolumnname;
    }

    public void setDbcolumnname(String dbcolumnname) {
        this.dbcolumnname = dbcolumnname;
    }

    public Integer getDatatype() {
        return datatype;
    }

    public void setDatatype(Integer datatype) {
        this.datatype = datatype;
    }

    public Integer getDatalength() {
        return datalength;
    }

    public void setDatalength(Integer datalength) {
        this.datalength = datalength;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public String getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(String isupdate) {
        this.isupdate = isupdate;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getSuperid() {
        return superid;
    }

    public void setSuperid(String superid) {
        this.superid = superid;
    }

    public String getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf;
    }

    public Integer getLevelno() {
        return levelno;
    }

    public void setLevelno(Integer levelno) {
        this.levelno = levelno;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getColformat() {
        return colformat;
    }

    public void setColformat(String colformat) {
        this.colformat = colformat;
    }

    public String getShowformat() {
        return showformat;
    }

    public void setShowformat(String showformat) {
        this.showformat = showformat;
    }

    public Integer getShowwidth() {
        return showwidth;
    }

    public void setShowwidth(Integer showwidth) {
        this.showwidth = showwidth;
    }

    public String getIskey() {
        return iskey;
    }

    public void setIskey(String iskey) {
        this.iskey = iskey;
    }

    public String getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(String isvisible) {
        this.isvisible = isvisible;
    }

    public String getIsreserve() {
        return isreserve;
    }

    public void setIsreserve(String isreserve) {
        this.isreserve = isreserve;
    }

    public String getIssum() {
        return issum;
    }

    public void setIssum(String issum) {
        this.issum = issum;
    }

    public String getIsregex() {
        return isregex;
    }

    public void setIsregex(String isregex) {
        this.isregex = isregex;
    }

    public String getRegexpr() {
        return regexpr;
    }

    public void setRegexpr(String regexpr) {
        this.regexpr = regexpr;
    }

    public String getRegexprinfo() {
        return regexprinfo;
    }

    public void setRegexprinfo(String regexprinfo) {
        this.regexprinfo = regexprinfo;
    }

    public String getIsbandcol() {
        return isbandcol;
    }

    public void setIsbandcol(String isbandcol) {
        this.isbandcol = isbandcol;
    }


    public String getBandcolumnid() {
		return bandcolumnid;
	}

	public void setBandcolumnid(String bandcolumnid) {
		this.bandcolumnid = bandcolumnid;
	}

	public String getBandrefdwcol() {
        return bandrefdwcol;
    }

    public void setBandrefdwcol(String bandrefdwcol) {
        this.bandrefdwcol = bandrefdwcol;
    }

    public String getExtprop() {
        return extprop;
    }

    public void setExtprop(String extprop) {
        this.extprop = extprop;
    }

    public String getColtips() {
        return coltips;
    }

    public void setColtips(String coltips) {
        this.coltips = coltips;
    }

    public String getFrmtabid() {
        return frmtabid;
    }

    public void setFrmtabid(String frmtabid) {
        this.frmtabid = frmtabid;
    }

    public String getFrmcolid() {
        return frmcolid;
    }

    public void setFrmcolid(String frmcolid) {
        this.frmcolid = frmcolid;
    }

    public String getIsvirtual() {
        return isvirtual;
    }

    public void setIsvirtual(String isvirtual) {
        this.isvirtual = isvirtual;
    }

    public String getVircontext() {
        return vircontext;
    }

    public void setVircontext(String vircontext) {
        this.vircontext = vircontext;
    }

    public String getBgtlvl() {
        return bgtlvl;
    }

    public void setBgtlvl(String bgtlvl) {
        this.bgtlvl = bgtlvl;
    }

	public List<DictTFactorPO> getDictTFactorList() {
		return dictTFactorList;
	}

	public void setDictTFactorList(List<DictTFactorPO> dictTFactorList) {
		this.dictTFactorList = dictTFactorList;
	}

	public Boolean getDrag() {
		return drag;
	}

	public void setDrag(Boolean drag) {
		this.drag = drag;
	}

	public Boolean getDropInner() {
		return dropInner;
	}

	public void setDropInner(Boolean dropInner) {
		this.dropInner = dropInner;
	}

	public Boolean getDropRoot() {
		return dropRoot;
	}

	public void setDropRoot(Boolean dropRoot) {
		this.dropRoot = dropRoot;
	}

	public Boolean getInner() {
		return inner;
	}

	public void setInner(Boolean inner) {
		this.inner = inner;
	}

	 /* @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((datalength == null) ? 0 : datalength.hashCode());
			result = prime * result
					+ ((datatype == null) ? 0 : datatype.hashCode());
			result = prime * result
					+ ((dbcolumnname == null) ? 0 : dbcolumnname.hashCode());
			result = prime * result
					+ ((defaultvalue == null) ? 0 : defaultvalue.hashCode());
			result = prime * result + ((iskey == null) ? 0 : iskey.hashCode());
			result = prime * result
					+ ((nullable == null) ? 0 : nullable.hashCode());
			result = prime * result + ((scale == null) ? 0 : scale.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DictTFactorPO other = (DictTFactorPO) obj;
			if (datalength == null) {
				if (other.datalength != null)
					return false;
			} else if (!datalength.equals(other.datalength))
				return false;
			if (datatype == null) {
				if (other.datatype != null)
					return false;
			} else if (!DictDBConstants.dataType.get(datatype).equals(DictDBConstants.dataType.get(other.datatype)))
				return false;
			if (dbcolumnname == null) {
				if (other.dbcolumnname != null)
					return false;
			} else if (!dbcolumnname.equals(other.dbcolumnname))
				return false;
			if (defaultvalue == null) {
				if (other.defaultvalue != null)
					return false;
			} else if (!defaultvalue.equals(other.defaultvalue))
				return false;
			if (iskey == null) {
				if (other.iskey != null)
					return false;
			} else if (!iskey.equals(other.iskey))
				return false;
			if (nullable == null) {
				if (other.nullable != null)
					return false;
			} else if (!nullable.equals(other.nullable))
				return false;
			if (scale == null) {
				if (other.scale != null)
					return false;
			} else if (!scale.equals(other.scale))
				return false;
			return true;
		}*/

	
	
		public boolean isOpen() {
			return open;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((bandrefdwcol == null) ? 0 : bandrefdwcol.hashCode());
			result = prime * result
					+ ((columnid == null) ? 0 : columnid.hashCode());
			result = prime * result
					+ ((datalength == null) ? 0 : datalength.hashCode());
			result = prime * result
					+ ((datatype == null) ? 0 : datatype.hashCode());
			result = prime * result
					+ ((dbcolumnname == null) ? 0 : dbcolumnname.hashCode());
			result = prime * result
					+ ((defaultvalue == null) ? 0 : defaultvalue.hashCode());
			result = prime * result + ((iskey == null) ? 0 : iskey.hashCode());
			result = prime * result
					+ ((nullable == null) ? 0 : nullable.hashCode());
			result = prime * result + ((scale == null) ? 0 : scale.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DictTFactorPO other = (DictTFactorPO) obj;
			if (bandrefdwcol == null) {
				if (other.bandrefdwcol != null)
					return false;
			} else if (!bandrefdwcol.equals(other.bandrefdwcol))
				return false;
			if (columnid == null) {
				if (other.columnid != null)
					return false;
			} else if (!columnid.equals(other.columnid))
				return false;
			if (datalength == null) {
				if (other.datalength != null)
					return false;
			} else if (!datalength.equals(other.datalength))
				return false;
			if (datatype == null) {
				if (other.datatype != null)
					return false;
			} else if (!DictDBConstants.dataType.get(datatype).equals(DictDBConstants.dataType.get(other.datatype)))
				return false;
			if (dbcolumnname == null) {
				if (other.dbcolumnname != null)
					return false;
			} else if (!dbcolumnname.equals(other.dbcolumnname))
				return false;
			if (defaultvalue == null) {
				if (other.defaultvalue != null)
					return false;
			} else if (!defaultvalue.equals(other.defaultvalue))
				return false;
			if (iskey == null) {
				if (other.iskey != null)
					return false;
			} else if (!iskey.equals(other.iskey))
				return false;
			if (nullable == null) {
				if (other.nullable != null)
					return false;
			} else if (!nullable.equals(other.nullable))
				return false;
			if (scale == null) {
				if (other.scale != null)
					return false;
			} else if (!scale.equals(other.scale))
				return false;
			if (isvirtual == null) {
				if (other.isvirtual != null)
					return false;
			} else if (!isvirtual.equals(other.isvirtual))
				return false;
			if (vircontext == null) {
				if (other.vircontext != null)
					return false;
			} else if (!vircontext.equals(other.vircontext))
				return false;
			return true;
		}

		public void setOpen(boolean open) {
			this.open = open;
		}

		public String getIshref() {
			return ishref;
		}

		public void setIshref(String ishref) {
			this.ishref = ishref;
		}

		public String getHrefloc() {
			return hrefloc;
		}

		public void setHrefloc(String hrefloc) {
			this.hrefloc = hrefloc;
		}

		public String getHrefparmid() {
			return hrefparmid;
		}

		public void setHrefparmid(String hrefparmid) {
			this.hrefparmid = hrefparmid;
		}

		public boolean isNocheck() {
			return nocheck;
		}

		public void setNocheck(boolean nocheck) {
			this.nocheck = nocheck;
		}

		public String getChangename() {
			return changename;
		}

		public void setChangename(String changename) {
			this.changename = changename;
		}

		public String getColumnoldid() {
			return columnoldid;
		}

		public void setColumnoldid(String columnoldid) {
			this.columnoldid = columnoldid;
		}

		public String getXlsxcolumnid() {
			return xlsxcolumnid;
		}

		public void setXlsxcolumnid(String xlsxcolumnid) {
			this.xlsxcolumnid = xlsxcolumnid;
		}

		public int getColumnindex() {
			return columnindex;
		}

		public void setColumnindex(int columnindex) {
			this.columnindex = columnindex;
		}

		public String getCsdbtablename() {
			return csdbtablename;
		}

		public void setCsdbtablename(String csdbtablename) {
			this.csdbtablename = csdbtablename;
		}

		
	 public String getOpenwindowtype() {
			return openwindowtype;
		}

		public void setOpenwindowtype(String openwindowtype) {
			this.openwindowtype = openwindowtype;
		}

	@Override
	    public Object clone() throws CloneNotSupportedException {
	        // TODO Auto-generated method stub
	        return super.clone();
	    }

    
}
