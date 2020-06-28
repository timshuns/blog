package com.timshuns.util;


import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/** 產生分頁 頁碼 */
public class PageUtil {

  public static <T> List<Integer> pageNumbers(Page<T> page) {
    List<Integer> result = new ArrayList<Integer>();
    int total = (int) page.getTotal();
    int current = (int) page.getCurrent();
    int pages = (int) page.getPages();

    // 總分頁數量小於5，當前頁小於等於3
    if (total <= 5 || current <= 3) {
      for (int i = 1; i <= pages; i++) {
        result.add(i);
      }
    } else if ((page.getCurrent() + 2) <= page.getPages()) {
      // 當前頁數+2 小於等於 最大頁數
      for (int i = (current - 2); i <= (current + 2); i++) {
        result.add(i);
      }
    } else {
      // 剩餘情況
      for (int i = (pages - 4); i <= pages; i++) {
        result.add(i);
      }
    }
    return result;
  }
}
