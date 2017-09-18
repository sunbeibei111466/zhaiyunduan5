package com.jlgproject.util;

/**
 * Created by moyan on 2017/8/29.
 */
public class PermissionModel {

    public int permissioncode;
    public String permission;
    public String explain;

    public PermissionModel(int permissioncode, String permission, String explain) {
        this.permissioncode = permissioncode;
        this.permission = permission;
        this.explain = explain;
    }
}
