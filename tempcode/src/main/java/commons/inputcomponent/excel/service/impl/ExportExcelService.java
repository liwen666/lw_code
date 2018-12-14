package commons.inputcomponent.excel.service.impl;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import com.tjhq.commons.inputcomponent.excel.service.IExportExcelService;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;

@Service
public class ExportExcelService implements IExportExcelService {

	/**
	 * 调用此方法需要三个参数
	 * CommonGrid  表头字段对象
	 * PageInfo 数据行字段对象
	 * HttpServletResponse  返回数据
	 */
	@Override
	public void exportExcel(Object obj,CommonGrid commonGrid,HttpServletResponse response) throws Exception{
		
		PageInfo pageInfo = (PageInfo) obj;//数据行
		CommonGrid grid = commonGrid;//表头列字段
		
		int row = 0; //记录表头占据多少行    控制表头
		int columnSize = 0;//记录当前列
		/**
		 * 循环得出表头有多少行
		 * 得到总共有多少列（合并的标题头除外）
		 */
		if(commonGrid != null){
			List<Column> oneList = commonGrid.getColumnList();//得到表头列
			if(oneList != null){
				for (int i = 0; i < oneList.size(); i++) {
					List<Column> twoList = oneList.get(i).getChildrenColumnList();//得到表头列
					if(twoList != null){
						for (int j = 0; j < twoList.size(); j++) {
							List<Column> threeList = twoList.get(j).getChildrenColumnList();//得到表头列
							if(threeList != null){
								for (int k = 0; k < threeList.size(); k++) {
									List<Column> fourList = threeList.get(k).getChildrenColumnList();//得到表头列
									if(fourList != null){
										for (int l = 0; l < fourList.size(); l++) {
											List<Column> fiveList = fourList.get(l).getChildrenColumnList();//得到表头列
											if(fiveList != null){
												for (int m = 0; m < fiveList.size(); m++) {
													//为列加1
													columnSize += 1;
												}
												//为行赋值
												row = 5;
											}else{
												//为列加1
												columnSize += 1;
											}
										}
									}else{
										//判断行数
										if(row == 5){
											//不做处理
										}else{
											//为行赋值
											row = 4;
										}
										//为列加1
										columnSize += 1;
									}
								}
							}else{
								//判断行数
								if(row == 5){
									//不做处理
								}else if(row == 4){
									//不做处理
								}else {
									//为行赋值
									row = 3;
								}
								//为列加1
								columnSize += 1;
							}
						}
					}else{
						//判断行数
						if(row == 5){
							//不做处理
						}else if(row == 4){
							//不做处理
						}else if(row == 3){
							//不做处理
						}else{
							//为行赋值
							row = 2;
						}
						//为列加1
						columnSize += 1;
					}
				}
			}else{
				//判断行数
				if(row == 5){
					//不做处理
				}else if(row == 4){
					//不做处理
				}else if(row == 3){
					//不做处理
				}else if(row == 2){
					//不做处理
				}else{
					//为行赋值
					row = 1;
				}
				//为列加1
				columnSize += 1;
			}
		}
		//调用生成表格方法
		this.createExcel(row,columnSize, pageInfo, grid, response);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void createExcel(int row,int columnSize ,PageInfo pageInfo,CommonGrid grid,HttpServletResponse response)throws Exception{
		
		String fileName = grid.getTableName() + ".xls";//设置导出excel表格名称
		String titleName = grid.getTableName();//excel内容标题名称
		
		/**
		 * 导出文件不能ajax方式导出
		 * 选择导出时，请解除注释
		 * 在此方法的下面需要修改写文件为OS
		 * 并且需要关闭OS
		 */
		
		OutputStream os = response.getOutputStream();
		response.reset();// 清空response
		// 设置response的Header
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		response.setContentType("application/x-xls");//设置为excel
		
		try {
			
			int oneListSize =0;
			int twoListSize = 0;
			int threeListSize = 0;
			int fourListSize = 0;
			int variate = 0;//记录数组坐标
			
			Map map = new HashMap();
			List<Column> oneList = grid.getColumnList();//得到表格内容
			
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet1 = book.createSheet("sheet1");
			
			// 创建单元格的格式，如居中、左对齐等
			HSSFCellStyle cellStyle = book.createCellStyle();
	        // 水平方向上居中对齐
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        // 垂直方向上居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框 
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框 
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
	        
	        // 创建字体，红色、粗体
			HSSFFont font = book.createFont();
			font.setFontName("黑体"); 
			//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short) 14);//设置字体大小 
	       
	        //设置字体
			cellStyle.setFont(font);
			//cellStyle.setWrapText(true);//设置自动换行
	        
	        HSSFRow  createRow;//创建行定义
	        
	        /**
	         * 生成excel表格
	         * 行 row
	         * 列 cell
	         */
	        for (int i= 0; i < row; i++) {
                //创建行
				createRow = sheet1.createRow(i);
				for (int j = 0; j < columnSize; j++) {
					createRow = sheet1.getRow(i);//得到行
					HSSFCell cell = createRow.createCell(j);//得到列
					cell.setCellStyle(cellStyle);//插入样式
					cell.setCellValue("");//插入数据
				}
            }
	        
	        columnSize = 0;
	        
			if(row == 5){
				if(oneList != null){
					for (int i = 0; i < oneList.size(); i++) {
						String oneName = oneList.get(i).getColumnName();//得到列名称
						String oneDBName = oneList.get(i).getColumnDBName();//得到数据列名称
						List<Column> twoList =  oneList.get(i).getChildrenColumnList();//得到包含的列集合
						twoListSize = 0;
						if(twoList != null){
							for (int j = 0; j < twoList.size(); j++) {
								String twoName = twoList.get(j).getColumnName();//得到列名称
								String twoDBName = twoList.get(j).getColumnDBName();//得到数据列名称
								List<Column> threeList =  twoList.get(j).getChildrenColumnList();//得到包含的列集合
								
								threeListSize = 0;
								if(threeList != null){
									
									for (int k = 0; k < threeList.size(); k++) {
										String threeName = threeList.get(k).getColumnName();//得到列名称
										String threeDBName = threeList.get(k).getColumnDBName();//得到数据列名称
										List<Column> fourList =  threeList.get(k).getChildrenColumnList();//得到包含的列集合
										
										fourListSize = 0;
										if(fourList != null){
											
											for (int l = 0; l < fourList.size(); l++) {
												String fourName = fourList.get(l).getColumnName();//得到列名称
												String fourDBName = fourList.get(l).getColumnDBName();//得到数据列名称
												List<Column> fiveList = fourList.get(l).getChildrenColumnList();//得到包含的列集合
												
												if(fiveList != null){
													oneListSize += fiveList.size();//记录要合并的列
													twoListSize += fiveList.size();//记录要合并的列
													threeListSize += fiveList.size();//记录要合并的列
													fourListSize += fiveList.size();//记录要合并的列
													for (int m = 0; m < fiveList.size(); m++) {
														String fiveName = fiveList.get(m).getColumnName();//得到列名称
														String fiveDBName = fiveList.get(m).getColumnDBName();//得到数据列名称
														createRow = sheet1.getRow(row);//得到行
														HSSFCell cell = createRow.createCell(columnSize);//添加表格
														cell.setCellStyle(cellStyle);//插入样式
														cell.setCellValue(fiveName);//插入数据
														columnSize += 1;
														
														map.put(variate, fiveDBName);//记录列对应的数据字段名称
														variate += 1;
													}
													
													createRow = sheet1.getRow(row-1);//得到行
													HSSFCell cell = createRow.createCell(columnSize-fiveList.size());//添加表格
													cell.setCellStyle(cellStyle);//插入样式
													cell.setCellValue(fourName);//插入数据
													//System.out.println(fourName);
													//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
													//int firstRow, int lastRow, int firstCol, int lastCol
													sheet1.addMergedRegion(new CellRangeAddress(row-1, row-1,columnSize-fiveList.size() ,columnSize-1));
													
													variate += 1;
												}else{
													
													createRow = sheet1.getRow(row-1);//得到行
													HSSFCell cell = createRow.createCell(columnSize);//添加表格
													cell.setCellStyle(cellStyle);//插入样式
													cell.setCellValue(fourName);//插入数据
													//System.out.println(fourName);
													//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
													//int firstRow, int lastRow, int firstCol, int lastCol
													sheet1.addMergedRegion(new CellRangeAddress(row-1,row,columnSize, columnSize));
													columnSize += 1;
													fourListSize += 1;
													threeListSize += 1;
													oneListSize += 1;
													
													map.put(variate, fourDBName);//记录列对应的数据字段名称
													variate += 1;
												}
											}
											
											createRow = sheet1.getRow(row-2);//得到行
											HSSFCell cell = createRow.createCell(columnSize - fourListSize);//添加表格
											cell.setCellStyle(cellStyle);//插入样式
											cell.setCellValue(threeName);//插入数据
											//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
											//int firstRow, int lastRow, int firstCol, int lastCol
											sheet1.addMergedRegion(new CellRangeAddress(row-2,row-2,columnSize - fourListSize,columnSize-1));
											
											variate += 1;
										}else{
											createRow = sheet1.getRow(row-2);//得到行
											HSSFCell cell = createRow.createCell(columnSize);//添加表格
											cell.setCellStyle(cellStyle);//插入样式
											cell.setCellValue(threeName);//插入数据
											//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
											//int firstRow, int lastRow, int firstCol, int lastCol  
											sheet1.addMergedRegion(new CellRangeAddress(row-2, row,columnSize ,columnSize));
											columnSize += 1;
											threeListSize += 1;
											oneListSize += 1;
											
											map.put(variate, threeDBName);//记录列对应的数据字段名称
											variate += 1;
										}
									}
									
									createRow = sheet1.getRow(row-3);//得到行
									HSSFCell cell = createRow.createCell(columnSize - threeListSize);//添加表格
									cell.setCellStyle(cellStyle);//插入样式
									cell.setCellValue(twoName);//插入数据
									//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
									//int firstRow, int lastRow, int firstCol, int lastCol
									sheet1.addMergedRegion(new CellRangeAddress(row-3,row-3,columnSize - threeListSize ,columnSize-1));
									
									variate += 1;
								}else{
									createRow = sheet1.getRow(row-3);//得到行
									HSSFCell cell = createRow.createCell(columnSize);//添加表格
									cell.setCellStyle(cellStyle);//插入样式
									cell.setCellValue(twoName);//插入数据
									//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
									//int firstRow, int lastRow, int firstCol, int lastCol
									sheet1.addMergedRegion(new CellRangeAddress(row-3,row , columnSize,columnSize));
									columnSize += 1;
									twoListSize += 1;
									oneListSize += 1;
									
									map.put(variate, twoDBName);//记录列对应的数据字段名称
									variate += 1;
								}
							}
							createRow = sheet1.getRow(row-4);//得到行
							HSSFCell cell = createRow.createCell(columnSize - oneListSize);//添加表格
							cell.setCellStyle(cellStyle);//插入样式
							cell.setCellValue(oneName);//插入数据
							//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
							//int firstRow, int lastRow, int firstCol, int lastCol
							sheet1.addMergedRegion(new CellRangeAddress(row-4, row-4,columnSize - oneListSize ,columnSize-1));
						
							variate += 1;
						}else{
							createRow = sheet1.getRow(row-4);//得到行
							HSSFCell cell = createRow.createCell(columnSize);//添加表格
							cell.setCellStyle(cellStyle);//插入样式
							cell.setCellValue(oneName);//插入数据
							//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
							//int firstRow, int lastRow, int firstCol, int lastCol
							sheet1.addMergedRegion(new CellRangeAddress(row-4,row , columnSize,columnSize));
							columnSize += 1;
							
							map.put(variate, oneDBName);//记录列对应的数据字段名称
							variate += 1;
						}
					}
					
				}
														
											
			}else if(row == 4){
				if(oneList != null){
					for (int i = 0; i < oneList.size(); i++) {
						String oneName = oneList.get(i).getColumnName();//得到列名称
						String oneDBName = oneList.get(i).getColumnDBName();//得到数据列名称
						List<Column> twoList =  oneList.get(i).getChildrenColumnList();//得到包含的列集合
						twoListSize = 0;
						if(twoList != null){
							for (int j = 0; j < twoList.size(); j++) {
								String twoName = twoList.get(j).getColumnName();//得到列名称
								String twoDBName = twoList.get(j).getColumnDBName();//得到数据列名称
								List<Column> threeList =  twoList.get(j).getChildrenColumnList();//得到包含的列集合
								
								threeListSize = 0;
								if(threeList != null){
									
									for (int k = 0; k < threeList.size(); k++) {
										String threeName = threeList.get(k).getColumnName();//得到列名称
										String threeDBName = threeList.get(k).getColumnDBName();//得到数据列名称
										List<Column> fourList =  threeList.get(k).getChildrenColumnList();//得到包含的列集合
										if(fourList != null){
											oneListSize += fourList.size();//记录要合并的列
											twoListSize += fourList.size();//记录要合并的列
											threeListSize += fourList.size();//记录要合并的列
											for (int l = 0; l < fourList.size(); l++) {
												String fourName = fourList.get(l).getColumnName();//得到列名称
												String fourDBName = fourList.get(l).getColumnDBName();//得到数据列名称
												createRow = sheet1.getRow(row);//得到行
												HSSFCell cell = createRow.createCell(columnSize);//添加表格
												cell.setCellStyle(cellStyle);//插入样式
												cell.setCellValue(fourName);//插入数据
												columnSize += 1;
												
												map.put(variate, fourDBName);//记录列对应的数据字段名称
												variate += 1;
											}
											
											createRow = sheet1.getRow(row-1);//得到行
											HSSFCell cell = createRow.createCell(columnSize-fourList.size());//添加表格
											cell.setCellStyle(cellStyle);//插入样式
											cell.setCellValue(threeName);//插入数据
											//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
											//int firstRow, int lastRow, int firstCol, int lastCol 
											sheet1.addMergedRegion(new CellRangeAddress(row-1, row-1,columnSize-fourList.size() ,columnSize-1));
											
											variate += 1;
										}else{
											createRow = sheet1.getRow(row-1);//得到行
											HSSFCell cell = createRow.createCell(columnSize);//添加表格
											cell.setCellStyle(cellStyle);//插入样式
											cell.setCellValue(threeName);//插入数据
											//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
											//int firstRow, int lastRow, int firstCol, int lastCol 
											sheet1.addMergedRegion(new CellRangeAddress(row-1, row,columnSize ,columnSize));
											columnSize += 1;
											threeListSize += 1;
											oneListSize += 1;
											
											map.put(variate, threeDBName);//记录列对应的数据字段名称
											variate += 1;
										}
									}
									
									createRow = sheet1.getRow(row-2);//得到行
									HSSFCell cell = createRow.createCell(columnSize - threeListSize);//添加表格
									cell.setCellStyle(cellStyle);//插入样式
									cell.setCellValue(twoName);//插入数据
									//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
									//int firstRow, int lastRow, int firstCol, int lastCol 
									sheet1.addMergedRegion(new CellRangeAddress(row-2, row-2,columnSize - threeListSize ,columnSize-1));
								
									variate += 1;
								}else{
									createRow = sheet1.getRow(row-2);//得到行
									HSSFCell cell = createRow.createCell(columnSize);//添加表格
									cell.setCellStyle(cellStyle);//插入样式
									cell.setCellValue(twoName);//插入数据
									//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
									//int firstRow, int lastRow, int firstCol, int lastCol 
									sheet1.addMergedRegion(new CellRangeAddress(row-2, row,columnSize ,columnSize));
									columnSize += 1;
									twoListSize += 1;
									oneListSize += 1;
									
									map.put(variate, twoDBName);//记录列对应的数据字段名称
									variate += 1;
								}
							}
							createRow = sheet1.getRow(row-3);//得到行
							HSSFCell cell = createRow.createCell(columnSize - oneListSize);//添加表格
							cell.setCellStyle(cellStyle);//插入样式
							cell.setCellValue(oneName);//插入数据
							//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列  
							//int firstRow, int lastRow, int firstCol, int lastCol
							sheet1.addMergedRegion(new CellRangeAddress(row-3, row-3,columnSize - oneListSize,columnSize-1));
						
							variate += 1;
						}else{
							createRow = sheet1.getRow(row-3);//得到行
							HSSFCell cell = createRow.createCell(columnSize);//添加表格
							cell.setCellStyle(cellStyle);//插入样式
							cell.setCellValue(oneName);//插入数据
							//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列   CellRangeAddress
							//int firstRow, int lastRow, int firstCol, int lastCol  
							sheet1.addMergedRegion(new CellRangeAddress(row-3, row,columnSize ,columnSize));
							columnSize += 1;
							
							map.put(variate, oneDBName);//记录列对应的数据字段名称
							variate += 1;
						}
					}
				}
			}else if(row == 3){
				if (oneList != null) {

					for (int i = 0; i < oneList.size(); i++) {
						String oneName = oneList.get(i).getColumnName();//得到列名称
						String oneDBName = oneList.get(i).getColumnDBName();//得到数据列名称
						List<Column> twoList =  oneList.get(i).getChildrenColumnList();//得到包含的列集合
						twoListSize = 0;
						if(twoList != null){
							for (int j = 0; j < twoList.size(); j++) {
								String twoName = twoList.get(j).getColumnName();//得到列名称
								String twoDBName = twoList.get(j).getColumnDBName();//得到数据列名称
								List<Column> threeList =  twoList.get(j).getChildrenColumnList();//得到包含的列集合
								
								threeListSize = 0;
								if(threeList != null){
									
									for (int k = 0; k < threeList.size(); k++) {
										String threeName = threeList.get(k).getColumnName();//得到列名称
										String threeDBName = threeList.get(k).getColumnDBName();//得到数据列名称
										
										createRow = sheet1.getRow(row);//得到行
										HSSFCell cell = createRow.createCell(columnSize);//添加表格
										cell.setCellStyle(cellStyle);//插入样式
										cell.setCellValue(threeName);//插入数据
										columnSize += 1;
										
										map.put(variate, threeDBName);//记录列对应的数据字段名称
										variate += 1;
									}
									
									createRow = sheet1.getRow(row-1);//得到行
									HSSFCell cell = createRow.createCell(columnSize-threeList.size());//添加表格
									cell.setCellStyle(cellStyle);//插入样式
									cell.setCellValue(twoName);//插入数据
									//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列   CellRangeAddress
									//int firstRow, int lastRow, int firstCol, int lastCol  
									sheet1.addMergedRegion(new CellRangeAddress(row-1, row-1,columnSize-threeList.size() ,columnSize-1));
									
									variate += 1;
								}else{
									createRow = sheet1.getRow(row-1);//得到行
									HSSFCell cell = createRow.createCell(columnSize);//天骄表格
									cell.setCellStyle(cellStyle);//插入样式
									cell.setCellValue(twoName);//插入数据
									//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列   CellRangeAddress
									//int firstRow, int lastRow, int firstCol, int lastCol  
									sheet1.addMergedRegion(new CellRangeAddress(row-1, row, columnSize,columnSize));
									columnSize += 1;
									twoListSize += 1;
									oneListSize += 1;
									
									map.put(variate, twoDBName);//记录列对应的数据字段名称
									variate += 1;
								}
							}
							createRow = sheet1.getRow(row-2);//得到行
							HSSFCell cell = createRow.createCell(columnSize - oneListSize);//添加表格
							cell.setCellStyle(cellStyle);//插入样式
							cell.setCellValue(oneName);//插入数据
							//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列   CellRangeAddress
							//int firstRow, int lastRow, int firstCol, int lastCol 
							sheet1.addMergedRegion(new CellRangeAddress(row-2, row-2 ,columnSize - oneListSize,columnSize-1));
						
							variate += 1;
						}else{
							createRow = sheet1.getRow(row-2);//得到行
							HSSFCell cell = createRow.createCell(columnSize);//添加表格
							cell.setCellStyle(cellStyle);//插入样式
							cell.setCellValue(oneName);//插入数据
							//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列   CellRangeAddress
							//int firstRow, int lastRow, int firstCol, int lastCol  
							sheet1.addMergedRegion(new CellRangeAddress(row-2, row,columnSize ,columnSize));
							columnSize += 1;
							
							map.put(variate, oneDBName); 
							variate += 1;
						}
					}
				
				}
			}else if(row == 2){
				if (oneList != null) {

					for (int i = 0; i < oneList.size(); i++) {
						String oneName = oneList.get(i).getColumnName();//得到列名称
						String oneDBName = oneList.get(i).getColumnDBName();//得到数据列名称
						List<Column> twoList =  oneList.get(i).getChildrenColumnList();//得到包含的列集合
						twoListSize = 0;
						if(twoList != null){
							for (int j = 0; j < twoList.size(); j++) {
								String twoName = twoList.get(j).getColumnName();//列名称
								String twoDBName = twoList.get(j).getColumnDBName();//数据库字段名称
								createRow = sheet1.getRow(row);//得到行
								HSSFCell cell = createRow.createCell(columnSize);//添加表格
								cell.setCellStyle(cellStyle);//插入样式
								cell.setCellValue(twoName);//插入数据
								columnSize += 1;
								
								map.put(variate, twoDBName);//记录列对应的数据字段名称
								variate += 1;
							}
							createRow = sheet1.getRow(row-1);//得到行
							HSSFCell cell = createRow.createCell(columnSize-twoList.size());//添加表格
							cell.setCellStyle(cellStyle);//插入样式
							cell.setCellValue(oneName);//插入数据
							//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列   CellRangeAddress
							//int firstRow, int lastRow, int firstCol, int lastCol
							sheet1.addMergedRegion(new CellRangeAddress(row-1, row-1 ,columnSize-twoList.size(),columnSize-1));
						
							variate += 1;
						}else{
							createRow = sheet1.getRow(row-1);//得到行
							HSSFCell cell = createRow.createCell(columnSize);//添加表格
							cell.setCellStyle(cellStyle);//插入样式
							cell.setCellValue(oneName);//插入数据
							//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列   CellRangeAddress
							//int firstRow, int lastRow, int firstCol, int lastCol  
							//sheet1.addMergedRegion(new CellRangeAddress(row-1, row,columnSize ,columnSize));
							columnSize += 1;
							
							map.put(variate, oneDBName);//记录列对应的数据字段名称
							variate += 1;
						}
					}
				
				}
			}else{
				for (int i = 0; i < oneList.size(); i++) {
					String oneName = oneList.get(i).getColumnName();//列名称
					String oneDBName = oneList.get(i).getColumnDBName();//列数据库字段名称
					createRow = sheet1.getRow(row);//得到行
					HSSFCell cell = createRow.createCell(columnSize);//添加表格
					cell.setCellStyle(cellStyle);//插入样式
					cell.setCellValue(oneName);//插入数据
					//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列   CellRangeAddress
					//int firstRow, int lastRow, int firstCol, int lastCol    
					//sheet1.addMergedRegion(new CellRangeAddress(row,  row,columnSize,columnSize));
					columnSize += 1;
					
					map.put(variate, oneDBName);
					variate += 1;
				}
			}
			
			for (int i = 0; i < columnSize-1; i++) {//循环设置列宽度
	        	sheet1.setColumnWidth(i, 5000); //第一个参数代表列id(从0开始),第2个参数代表宽度值
	        }
			
			/**
			 * 添加表头
			 */
			createRow = sheet1.getRow(0);//得到行
			HSSFCell cell = createRow.createCell(0);//添加表格
			cell.setCellStyle(cellStyle);//插入样式
			cell.setCellValue(titleName);//插入数据
			//合并单元格	四个参数分别是：起始行，结束行，起始列，结束列   CellRangeAddress
			//int firstRow, int lastRow, int firstCol, int lastCol
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0,columnSize-1));
			
			List<Object> dataList = pageInfo.getDataList();//得到list数据
			List<String> columns = pageInfo.getColumns();//得到列数据
			for (int i = 0; i < dataList.size(); i++) {
				
				//得到数据
				String aaa = dataList.get(i).toString();//得到数据
				String bbb = (String) aaa.substring(1, aaa.length()-1);//因得到数据中包含（[]）中括号，所以要去掉第一个和左后一个字符
				String ccc[] = bbb.split(",");//获取数组内容
				
				createRow = sheet1.createRow(row);//创建航
				for (int j = 0; j < columnSize; j++) {
					createRow = sheet1.getRow(row);//得到行
					HSSFCell cellCell = createRow.createCell(j);//创建列
					cellCell.setCellStyle(cellStyle);//插入样式
				}
				
				Object s[] = map.keySet().toArray();//得到记录列数据
				for (int j2 = 0; j2 < map.size(); j2++) {
					String columnOne = (String) map.get(s[j2]);//得到记录数据内容
					
					/**
					 * 判断是否为null
					 * 为null时，置为空值
					 * 为下面for循环做准备
					 */
					if(columnOne == null){
						columnOne ="";
					}
					
					for (int j = 0; j < columns.size(); j++) {
						String columnTwo = columns.get(j);
						if(columnOne.equals("") || columnOne == null || columnOne.equals("null")){
							//只插入表格，不处理表格
							createRow = sheet1.getRow(row);//得到行
							HSSFCell cell1 = createRow.createCell(j2);//得到列
							cell1.setCellStyle(cellStyle);//插入样式
						}else if(columnOne.equals(columnTwo)){
							String bodyTitleName = ccc[j].replace(" ", "");//去掉空格
							
							createRow = sheet1.getRow(row);//得到行
							HSSFCell cell1 = createRow.createCell(j2);//添加单元格
							cell1.setCellStyle(cellStyle);//插入样式
							if(bodyTitleName == null || bodyTitleName.equals("null") || bodyTitleName.equals("")){
								//不做任何处理，如果不对此项进行判断，则表格中会插入null
							}else{
								cell1.setCellValue(bodyTitleName);//插入数据
							}
						}
					}
				}
				//在原有行的基础上，在添加一行
				row += 1;
			}
	        //FileOutputStream fileOut =new FileOutputStream("d://excel.xls");
            //book.write(fileOut);
            book.write(os);    //导出需要os,替换前面fileOut
            //fileOut.close();
		} finally{
			if(os != null){
				os.close();		//需要关闭os
			}
		}
	}
}
