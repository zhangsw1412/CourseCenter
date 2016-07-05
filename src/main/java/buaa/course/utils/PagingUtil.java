package buaa.course.utils;

import org.springframework.stereotype.Service;

@Service
public class PagingUtil {
	// 声明一些常量
	final public static String FIRSTPAGE = "first";// 请求的是首页
	final public static String PREVIOUSPAGE = "previous";// 请求上一页
	final public static String NEXTPAGE = "next";// 请求下一页
	final public static String LASTPAGE = "last";// 请求最后一页

	// 声明一些变量
	private int pageRow = 3;// 每页显示记录数
	private int totalRow = 0;// 总的记录数，有数据库操作类DAO提供
	private int currentPage = 1;// 当前的页面
	private int firstPage = 1;// 首页位置
	private int totalPage = 1;// 总的页面数,默认为一页

	public int getPageRow() {
		return pageRow;
	}

	/**
	 * 重新设置每页显示的记录数
	 * 
	 * @param pageRow
	 *            新的每页显示记录数
	 */
	public void setPageRow(int pageRow) {
		if (pageRow == 0) {
			throw new IllegalArgumentException("每页记录数不能为零！");
		}

		this.pageRow = pageRow;// 修改每页的记录数
		this.totalPage = this.totalRow / this.pageRow
				+ ((this.totalRow % this.pageRow == 0) ? 0 : 1);
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {// 设置分页对象的总记录属性后,就应该根据每页面显示记录数,计算得到总的页面数
		this.totalRow = totalRow;
		this.totalPage = this.totalRow/this.pageRow+((this.totalRow%this.pageRow==0)?0:1);

	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getTotalPage() {
		return totalPage;
	}
	//不应该提供方法设置总页面数,它是计算得到的 

	/**
	 * 根据请求的标示符参数重新计算要现实的页面
	 * 
	 * @param flag
	 *            请求转向的页面标示符
	 * @return int 返回新页
	 */
	public int toNewPage(String flag) {
		int newPage = currentPage;
		if (flag != null && !"".equals(flag)) {
			if (FIRSTPAGE.equals(flag)) {
				newPage = 1;
			} else if (LASTPAGE.equals(flag)) {
				newPage = this.totalPage;
			} else if (NEXTPAGE.equals(flag)) {
				newPage = this.currentPage
						+ ((this.currentPage == this.totalPage) ? 0 : 1);// 如果当前页面与总的页面数相等则不再向后（+1）
			} else if (PREVIOUSPAGE.equals(flag)) {
				newPage = this.currentPage
						- ((this.currentPage == this.firstPage) ? 0 : 1);// 如果当前页面与首页相等则不再向前（-1）
			} else {
				// 传入的是个数字字符串参数
				try{
					newPage = Integer.parseInt(flag.trim());
					if(newPage > totalPage)
						newPage = totalPage;
					if(newPage < 1)
						newPage = 1;
				}catch (NumberFormatException e){
					newPage = currentPage;
				}
			}
		} else {// 请求的参数为空，则当前页码不变
			newPage = this.currentPage;
		}
		this.setCurrentPage(newPage);// 记得重新设置当期页面
		return newPage;
	}

	public int getFirstIndex() {
		return pageRow*(currentPage-1) < 0 ? 0 : pageRow*(currentPage-1);
	}

	public int getLastIndex() {
		return getFirstIndex() + pageRow -1 > getTotalRow() ? getTotalRow()-1 :getFirstIndex() + pageRow -1;
	}

	@Override
	public String toString() {
		return "SplitPage [pageRow=" + pageRow + ", totalRow=" + totalRow + ", currentPage=" + currentPage
				+ ", firstPage=" + firstPage + ", totalPage=" + totalPage + "]";
	}
	
	
}