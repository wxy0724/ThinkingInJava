package com.fang.designpattern;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 建造模式
 */
public class BuildMedia {

    private List input = Arrays.asList(new MediaItem[]{new MediaItem("item1"), new MediaItem("item2"), new MediaItem("item3"), new MediaItem("item4")});


//    public void testBook() {
//        MediaDirector buildBook = new MediaDirector(input);
//        Media book = buildBook.produceMedia(input);
//        String result = "book:" + book;
//        System.out.println(result);
//        assertEquals(result, "book:[item1,item2,item3,item4]");
//    }



}
// 不同媒体形式
class Media extends ArrayList {
}

class Book extends Media {
}

class Magazine extends Media {
}

class WebSite extends Media {
}

// 进而包含不同的媒体组成元素
class MediaItem {
    private String s;

    public MediaItem(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }
}

class Chapter extends MediaItem {
    public Chapter(String s) {
        super(s);
    }
}

class Article extends MediaItem {
    public Article(String s) {
        super(s);
    }
}

class WebItem extends MediaItem {
    public WebItem(String s) {
        super(s);
    }
}

// 抽象建造者角色，它规范了所有媒体建造的步骤
class MediaBuilder {
    public void buildBase() {
    }

    public void addMediaItem(MediaItem item) {

    }

    public Media getFinishedMedia() {
        return null;
    }
}

// 具体建造者模式
class BookBuilder extends MediaBuilder {
    private Book b;

    public void bulidBase() {
        System.out.println("Building book framework");
        b = new Book();
    }

    @Override
    public void addMediaItem(MediaItem chapter) {
        System.out.println("Adding chapter" + chapter);
        b.add(chapter);
    }

    @Override
    public Media getFinishedMedia() {
        return b;
    }
}

class MagazineBuilder extends MediaBuilder {
    private Magazine m;

    public void bulidBase() {
        System.out.println("Building magazine framework");
        m = new Magazine();
    }

    @Override
    public void addMediaItem(MediaItem artical) {
        System.out.println("Adding chapter" + artical);
        m.add(artical);
    }

    @Override
    public Media getFinishedMedia() {
        return m;
    }
}

class WebsiteBuilder extends MediaBuilder {
    private WebSite w;

    public void bulidBase() {
        System.out.println("Building website framework");
        w = new WebSite();
    }

    @Override
    public void addMediaItem(MediaItem webItem) {
        System.out.println("Adding chapter" + webItem);
        w.add(webItem);
    }

    @Override
    public Media getFinishedMedia() {
        return w;
    }
}

class BookBuild extends MediaBuilder {
    private Book b;

    public void bulidBase() {
        System.out.println("Building book framework");
        b = new Book();
    }

    @Override
    public void addMediaItem(MediaItem chapter) {
        System.out.println("Adding chapter" + chapter);
        b.add(chapter);
    }

    @Override
    public Media getFinishedMedia() {
        return b;
    }
}

// 指导者角色，也叫上下文
class MediaDirector {
    private MediaBuilder mb;

    public MediaDirector(MediaBuilder mb) {
        this.mb = mb;// 具有策略模式相似特征的
    }

    public Media produceMedia(List input) {
        mb.buildBase();
        for (Iterator it = input.iterator(); it.hasNext(); ) {
            mb.addMediaItem((MediaItem) it.next());
        }
        return mb.getFinishedMedia();
    }
}
