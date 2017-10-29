/*
 * Copyright (C) 2017 TripNDroid Mobile Engineering
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tripndroid.dolby.audio.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DsProfileName implements Parcelable {
    public static final Creator<DsProfileName> CREATOR = new Creator<DsProfileName>() {
        public DsProfileName createFromParcel(Parcel source) {
            return new DsProfileName(source);
        }

        public DsProfileName[] newArray(int size) {
            return new DsProfileName[size];
        }
    };
    private static final String TAG = "DsProfileName";
    private String currentName_;
    private String defaultName_;

    public DsProfileName() {
    }
    
    public DsProfileName(final Parcel src) {
        this.readFromParcel(src);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.currentName_);
        dest.writeString(this.defaultName_);
    }

    public void readFromParcel(Parcel src) {
        this.currentName_ = src.readString();
        this.defaultName_ = src.readString();
    }

    public void setCurrentName(String name) {
        this.currentName_ = name;
    }

    public String getCurrentName() {
        return this.currentName_;
    }

    public void setDefaultName(String name) {
        this.defaultName_ = name;
    }

    public String getDefaultName() {
        return this.defaultName_;
    }
}
