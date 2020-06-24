package com.timshuns.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/** 產生分頁 頁碼 */
public class PageUtil {

  public static <T> int[] pageNumbers(Page<T> page) {
    int[] result = new int[5];
System.err.println(page.getPages());
    // 總分頁數量小於5，當前頁小於等於3
    if (page.getTotal() <= 5L || page.getCurrent() <= 3L) {
      for (int i = 0; i < 5; i++) {
        result[i] = (i + 1);
      }
    } else if ((page.getCurrent() + 2) <= page.getPages()) {
      // 當前頁數+2 小於等於 最大頁數
      result[0] = (int) page.getCurrent() - 2;
      result[1] = (int) page.getCurrent() - 1;
      result[2] = (int) page.getCurrent();
      result[3] = (int) page.getCurrent() + 1;
      result[4] = (int) page.getCurrent() + 2;
    } else {
      result[0] = (int) page.getPages() - 4;
      result[1] = (int) page.getPages() - 3;
      result[2] = (int) page.getPages() - 2;
      result[3] = (int) page.getPages() - 1;
      result[4] = (int) page.getPages();
    }
    return result;
  }
}
