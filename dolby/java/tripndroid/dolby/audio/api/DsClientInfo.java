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

public class DsClientInfo implements Parcelable
{
    public static final Creator<DsClientInfo> CREATOR;
    private static final String TAG = "DsClientInfo";
    private int mConnectionBridge;
    private String mPackageName;

    static {
        CREATOR = (Creator)new Creator<DsClientInfo>() {
            public DsClientInfo createFromParcel(final Parcel parcel) {
                return new DsClientInfo(parcel);
            }
            
            public DsClientInfo[] newArray(final int n) {
                return new DsClientInfo[n];
            }
        };
    }

    public DsClientInfo() {
    }

    public DsClientInfo(final Parcel parcel) {
        this.readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public int getConnectionBridge() {
        return this.mConnectionBridge;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void readFromParcel(final Parcel parcel) {
        this.mPackageName = parcel.readString();
        this.mConnectionBridge = parcel.readInt();
    }

    public void setConnectionBridge(final int mConnectionBridge) {
        this.mConnectionBridge = mConnectionBridge;
    }

    public void setPackageName(final String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.mPackageName);
        parcel.writeInt(this.mConnectionBridge);
    }
}
