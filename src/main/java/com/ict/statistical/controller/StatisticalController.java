package com.ict.statistical.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.ict.business.domain.Customer;
import com.ict.business.service.CustomerService;
import com.ict.statistical.domain.BaseEntity;
import com.ict.statistical.service.StatisticalService;
import com.ict.system.constant.constant;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;


/**
 * 统计分析的控制器
 *
 * @Author: Lizbeth9421
 * @Date: 2021/08/16/15:23
 */
@Controller
@RequestMapping("/stat")
public class StatisticalController {
    @Autowired
    private StatisticalService service;

    @Autowired
    private CustomerService customerService;

    /**
     * 跳转到客户地区统计
     *
     * @return
     */
    @RequestMapping("/toCustomerAreaStat")
    public String toCustomerAreaStat() {
        return "forward:/views/statistical/customerAreaStat.html";
    }

    /*
     * 加载客户地区数据
     */
    @ResponseBody
    @RequestMapping("/loadCustomerAreaStatistical")
    public List<BaseEntity> loadCustomerAreaStatistical() {
        return this.service.loadCustomerAreaStatistical();
    }

    /**
     * 跳转到业务员年度销售额
     *
     * @return
     */
    @RequestMapping("/toOpernameYearGradeStat")
    public String toOpernameYearGradeStat() {
        return "forward:/views/statistical/opernameYearGradeStat.html";
    }

    /**
     * 加载业务员年度销售额数据
     *
     * @param year
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadOpernameYearGradeStat")
    public Map<String, Object> loadOpernameYearGradeStat(final String year) {
        final List<BaseEntity> list = this.service.loadOpernameYearGradeStat(year);
        final List<String> names = new ArrayList<>();
        final List<Double> values = new ArrayList<>();
        for (final BaseEntity base : list) {
            names.add(base.getName());
            values.add(base.getValue());
        }
        final Map<String, Object> map = new HashMap<>();
        map.put("name", names);
        map.put("value", values);
        return map;
    }

    /**
     * 跳转到业务员年度销售额
     *
     * @return
     */
    @RequestMapping("/toComponyYearGradeStat")
    public String toComponyYearGradeStat() {
        return "forward:/views/statistical/componyYearGradeStat.html";
    }

    /**
     * 加载业务员年度销售额数据
     *
     * @param year
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadComponyYearGradeStat")
    public List<Double> loadComponyYearGradeStat(final String year) {
        final List<Double> list = this.service.loadComponyYearGradeStat(year);
        //对为null的数据进行处理
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                list.set(i, 0.0);
            }
        }
        return list;
    }

    /**
     * 客户管理中的数据导出
     *
     * @param customer
     * @param resp
     */
    @RequestMapping("/customerExport")
    public void customerExport(final Customer customer, final HttpServletResponse resp) {
        final List<Customer> list = this.customerService.queryAllCustomerForList(customer);
        final List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> map = null;
        //组装数据
        for (final Customer c : list) {
            map = new LinkedHashMap<>();
            map.put("身份证号", c.getIdentity());
            map.put("用户名", c.getCustname());
            map.put("性别", c.getSex() == 1 ? "男" : "女");
            map.put("地址", c.getAddress());
            map.put("手机号码", c.getPhone());
            map.put("职业", c.getCareer());
            map.put("创建时间", c.getCreatetime().toLocaleString());
            data.add(map);
        }
        final ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(data);
        // 通过工具类创建writer，默认创建xls格式
        final ExcelWriter writer = ExcelUtil.getWriter();
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(6, "客户数据");
        //设置所有数据居中
        final StyleSet styleSet = writer.getStyleSet();
        styleSet.setAlign(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
        //一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        resp.setContentType("application/vnd.ms-excel;charset=utf-8");
        ServletOutputStream out = null;
        try {
            String fileName = constant.CUSTOMER_EXPORT_NAME;
            //中文编码乱码处理
            fileName = URLEncoder.encode(fileName, "UTF-8");
            resp.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
            out = resp.getOutputStream();
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            //设置所有列为自动宽度
            //final SXSSFSheet sheet = (SXSSFSheet) writer.getSheet();
            //sheet.trackAllColumnsForAutoSizing();
            writer.autoSizeColumnAll();
            //将Excel Workbook刷出到预定义的文件
            writer.flush(out, true);
            // 关闭writer，释放内存
            writer.close();
            //此处记得关闭输出Servlet流
            IoUtil.close(out);
        }

    }
}
