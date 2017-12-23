package com.kailaisi.pojo;

import com.kailaisi.bean.BaseBean;

public class Update extends BaseBean {
    private Integer id;

    private String md5value;

    private Integer versioncode;

    private String versionname;

    private Integer newversioncode;

    private String newversionname;

    private Long filesize;

    private String downloadpath;

    private String patchdownloadpath;

    private String channelid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMd5value() {
        return md5value;
    }

    public void setMd5value(String md5value) {
        this.md5value = md5value == null ? null : md5value.trim();
    }

    public Integer getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(Integer versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname == null ? null : versionname.trim();
    }

    public Integer getNewversioncode() {
        return newversioncode;
    }

    public void setNewversioncode(Integer newversioncode) {
        this.newversioncode = newversioncode;
    }

    public String getNewversionname() {
        return newversionname;
    }

    public void setNewversionname(String newversionname) {
        this.newversionname = newversionname == null ? null : newversionname.trim();
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public String getDownloadpath() {
        return downloadpath;
    }

    public void setDownloadpath(String downloadpath) {
        this.downloadpath = downloadpath == null ? null : downloadpath.trim();
    }

    public String getPatchdownloadpath() {
        return patchdownloadpath;
    }

    public void setPatchdownloadpath(String patchdownloadpath) {
        this.patchdownloadpath = patchdownloadpath == null ? null : patchdownloadpath.trim();
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid == null ? null : channelid.trim();
    }
}