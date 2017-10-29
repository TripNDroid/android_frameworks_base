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

package tripndroid.dolby.audio;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.HashSet;

public class DsClientSettings implements Parcelable {
    public static final Creator<DsClientSettings> CREATOR = new Creator<DsClientSettings>() {
        public DsClientSettings createFromParcel(Parcel source) {
            return new DsClientSettings(source);
        }

        public DsClientSettings[] newArray(int size) {
            return new DsClientSettings[size];
        }
    };
    private static final String TAG = "DsClientSettings";
    public static final HashSet<String> basicProfileParams = new HashSet();
    private boolean isDialogEnhancerOn;
    private boolean isGeqOn;
    private boolean isHeadphoneVirtualizerOn;
    private boolean isSpeakerVirtualizerOn;
    private boolean isVolumeLevellerOn;

    static {
        basicProfileParams.add("geon");
        basicProfileParams.add("deon");
        basicProfileParams.add("dvle");
        basicProfileParams.add("vdhe");
        basicProfileParams.add("vspe");
    }

    public int describeContents() {
        return 0;
    }

    public DsClientSettings() {
        this.isGeqOn = false;
        this.isDialogEnhancerOn = false;
        this.isVolumeLevellerOn = false;
        this.isHeadphoneVirtualizerOn = false;
        this.isSpeakerVirtualizerOn = false;
    }

    public DsClientSettings(Parcel src) {
        readFromParcel(src);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBooleanArray(new boolean[]{this.isGeqOn, this.isDialogEnhancerOn, this.isVolumeLevellerOn, this.isHeadphoneVirtualizerOn, this.isSpeakerVirtualizerOn});
    }

    public void readFromParcel(Parcel src) {
        boolean[] settings = new boolean[5];
        src.readBooleanArray(settings);
        this.isGeqOn = settings[0];
        this.isDialogEnhancerOn = settings[1];
        this.isVolumeLevellerOn = settings[2];
        this.isHeadphoneVirtualizerOn = settings[3];
        this.isSpeakerVirtualizerOn = settings[4];
    }

    public void setGeqOn(boolean enable) {
        this.isGeqOn = enable;
    }

    public boolean getGeqOn() {
        return this.isGeqOn;
    }

    public void setDialogEnhancerOn(boolean enable) {
        this.isDialogEnhancerOn = enable;
    }

    public boolean getDialogEnhancerOn() {
        return this.isDialogEnhancerOn;
    }

    public void setVolumeLevellerOn(boolean enable) {
        this.isVolumeLevellerOn = enable;
    }

    public boolean getVolumeLevellerOn() {
        return this.isVolumeLevellerOn;
    }

    public void setHeadphoneVirtualizerOn(boolean enable) {
        this.isHeadphoneVirtualizerOn = enable;
    }

    public boolean getHeadphoneVirtualizerOn() {
        return this.isHeadphoneVirtualizerOn;
    }

    public void setSpeakerVirtualizerOn(boolean enable) {
        this.isSpeakerVirtualizerOn = enable;
    }

    public boolean getSpeakerVirtualizerOn() {
        return this.isSpeakerVirtualizerOn;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof DsClientSettings) {
            DsClientSettings anotherObject = (DsClientSettings) anObject;
            if (this.isGeqOn == anotherObject.isGeqOn && this.isDialogEnhancerOn == anotherObject.isDialogEnhancerOn && this.isVolumeLevellerOn == anotherObject.isVolumeLevellerOn && this.isHeadphoneVirtualizerOn == anotherObject.isHeadphoneVirtualizerOn && this.isSpeakerVirtualizerOn == anotherObject.isSpeakerVirtualizerOn) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.isGeqOn) {
            i = 2;
        } else {
            i = 0;
        }
        int result = i + 0;
        if (this.isDialogEnhancerOn) {
            i = 4;
        } else {
            i = 0;
        }
        result += i;
        if (this.isVolumeLevellerOn) {
            i = 8;
        } else {
            i = 0;
        }
        result += i;
        if (this.isHeadphoneVirtualizerOn) {
            i = 16;
        } else {
            i = 0;
        }
        result += i;
        if (this.isSpeakerVirtualizerOn) {
            i2 = 32;
        }
        return result + i2;
    }
}
