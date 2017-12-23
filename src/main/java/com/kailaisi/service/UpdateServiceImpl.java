package com.kailaisi.service;

import com.kailaisi.bean.UpdateBean;
import com.kailaisi.mapper.UpdateMapper;
import com.kailaisi.pojo.Update;
import com.kailaisi.utils.CommonUtils;
import com.ywl5320.bsdiff.BsDiffYwl5320Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Service("updateService")
public class UpdateServiceImpl {
    static String oldApksPath = "E:/source/oldversion";
    static String newApkPath = "E:/source/newversion";
    static String patchPath = "E:/source/patch";
    @Resource
    UpdateMapper updateMapper;

    static {
        if (CommonUtils.getOsName().contains("windows")) {
            oldApksPath = "E:/source/oldversion";
            newApkPath = "E:/source/newversion";
            patchPath = "E:/source/patch";
        }
    }


    /**
     * 检测更新
     *
     * @return
     */
    public UpdateBean checkUpdate(String md5Value, int versioncode, String channelid) {
        Update updateInfo = updateMapper.getUpdateInfo(md5Value, versioncode, channelid);
        if (updateInfo != null) {
            File file = new File(patchPath + "/" + updateInfo.getPatchdownloadpath());
            if (file.exists()) {
                UpdateBean bean = getUpdateBean(true, true, updateInfo.getNewversionname(), updateInfo.getPatchdownloadpath());
                return bean;
            }
            updateMapper.deleteByPrimaryKey(updateInfo.getId());
            return getUpdateBean(false, false, "", "");
        } else {
            Update update = creatPatch(md5Value, versioncode, channelid);
            if (update == null) {
                return getUpdateBean(false, false, "", "");
            } else {
                return getUpdateBean(true, true, update.getNewversionname(), update.getPatchdownloadpath());
            }
        }
    }

    /**
     * 生成增量包
     */
    private Update creatPatch(String md5Value, int versioncode, String channelid) {
        File md5File = null;
        String versionName = null;
        File newFile = null;
        int newVersionCode = 0;
        String newVersionName = null;
        String patchName = null;
        File file = new File(oldApksPath);
        if (!file.exists()) {
            return null;
        }
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }
        for (File f : files) {//根据md5值找到和客户端相同的版本
            String md5 = CommonUtils.getFileMd5(f);
            if (md5Value.equals(md5)) {
                System.out.println(f.getName() + "的md5:" + md5);
                String[] flag = f.getName().replace(".apk", "").split("_");
                if (flag.length == 4 && flag[3].equals(channelid)) {
                    versionName = flag[2];
                    md5File = f;
                    break;
                }
            }
        }
        if (md5File == null) {
            return null;
        }
        File nfile = new File(newApkPath);
        if (!nfile.exists()) {
            return null;
        }
        File[] nfiles = nfile.listFiles();
        if (nfiles == null || nfiles.length == 0) {
            return null;
        }
        for (File nf : nfiles) {
            String[] flag = nf.getName().replace(".apk", "").split("_");
            if (flag.length == 4 && flag[3].equals(channelid)) {
                System.out.println("渠道：" + channelid + " 的当前最新版本" + nf.getName());
                newFile = nf;
                newVersionCode = Integer.parseInt(flag[1]);
                newVersionName = flag[2];
                patchName = patchPath + "/" + nf.getName().replace(".apk", "") + "_patch_" + versioncode + ".patch";
                break;
            }
        }
        if (newFile == null)
            return null;

        System.out.println("oldfile:" + md5File.getAbsolutePath());
        System.out.println("newfile:" + newFile.getAbsolutePath());
        System.out.println("patchfile:" + patchName);

        int result = BsDiffYwl5320Util.getInstance().bsDiffFile(md5File.getAbsolutePath(), newFile.getAbsolutePath(), patchName);
        if (result != 0)
            return null;

        File patchFile = new File(patchName);
        if (!patchFile.exists())
            return null;
        Update updateBean = new Update();
        updateBean.setMd5value(md5Value);
        updateBean.setVersioncode(versioncode);
        updateBean.setVersionname(versionName);
        updateBean.setNewversioncode(newVersionCode);
        updateBean.setNewversionname(newVersionName);
        updateBean.setFilesize(md5File.length());
        updateBean.setFilesize(patchFile.length());
        updateBean.setDownloadpath(newFile.getName());
        updateBean.setPatchdownloadpath(patchFile.getName());
        updateBean.setChannelid(channelid);
        return updateBean;
    }

    private UpdateBean getUpdateBean(boolean force, boolean hasNew, String version, String patchPath) {
        UpdateBean bean = new UpdateBean();
        bean.setForce(force);
        bean.setHasNew(hasNew);
        bean.setNewVersion(version);
        bean.setPath(patchPath);
        return bean;
    }
}
