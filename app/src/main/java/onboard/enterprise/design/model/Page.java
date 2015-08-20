package onboard.enterprise.design.model;

/**
 * Created by snambiar on 7/07/15.
 */
public class Page {

    String bgColor;
    int    pageNum;
    int    imgResId;


    public Page(){


    }

    public Page(int pPageNum,String pBgColor,int pImgResId){

        this.bgColor = pBgColor;
        this.pageNum = pPageNum;
        this.imgResId = pImgResId;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getBgColor() {

        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
