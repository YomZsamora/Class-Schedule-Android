package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class ModulesApiResponse {
    @SerializedName("students")
    @Expose
    public ArrayList<ModuleModel> modules = null;

    public ModulesApiResponse() {
    }

    public ModulesApiResponse(ArrayList<ModuleModel> modules) {
        this.modules = modules;
    }

    public ArrayList<ModuleModel> getModules() {
        return modules;
    }

    public void setModules(ArrayList<ModuleModel> modules) {
        this.modules = modules;
    }
}
