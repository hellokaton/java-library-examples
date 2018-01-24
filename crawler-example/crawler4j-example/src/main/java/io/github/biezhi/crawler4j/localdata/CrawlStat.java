package io.github.biezhi.crawler4j.localdata;

import lombok.Data;

@Data
public class CrawlStat {
    private int  totalProcessedPages;
    private long totalLinks;
    private long totalTextSize;

    public void incTotalLinks(int count) {
        this.totalLinks += count;
    }

    public void incTotalTextSize(int count) {
        this.totalTextSize += count;
    }
}