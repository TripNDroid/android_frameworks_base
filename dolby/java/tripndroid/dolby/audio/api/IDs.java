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

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDs extends IInterface {

    public static abstract class Stub extends Binder implements IDs {
        private static final String DESCRIPTOR = "tripndroid.dolby.audio.api.IDs";
        static final int TRANSACTION_iAbandonAccessRight = 26;
        static final int TRANSACTION_iCheckAccessRight = 27;
        static final int TRANSACTION_iGetDapLibraryVersion = 13;
        static final int TRANSACTION_iGetDsServiceVersion = 12;
        static final int TRANSACTION_iGetIeqPreset = 18;
        static final int TRANSACTION_iGetIeqPresetCount = 19;
        static final int TRANSACTION_iGetMonoSpeaker = 29;
        static final int TRANSACTION_iGetOffType = 11;
        static final int TRANSACTION_iGetParamLength = 28;
        static final int TRANSACTION_iGetParameter = 16;
        static final int TRANSACTION_iGetProfile = 21;
        static final int TRANSACTION_iGetProfileCount = 24;
        static final int TRANSACTION_iGetProfileEndpointParameter = 33;
        static final int TRANSACTION_iGetProfileModified = 23;
        static final int TRANSACTION_iGetProfileName = 31;
        static final int TRANSACTION_iGetProfileParameter = 32;
        static final int TRANSACTION_iGetProfilePortParameter = 34;
        static final int TRANSACTION_iGetState = 10;
        static final int TRANSACTION_iGetTuningParameter = 35;
        static final int TRANSACTION_iGetUdcLibraryVersion = 14;
        static final int TRANSACTION_iRegisterCallback = 7;
        static final int TRANSACTION_iRegisterDeathHandler = 3;
        static final int TRANSACTION_iRegisterDsAccess = 5;
        static final int TRANSACTION_iRegisterVisualizerData = 1;
        static final int TRANSACTION_iRequestAccessRight = 25;
        static final int TRANSACTION_iResetProfile = 22;
        static final int TRANSACTION_iSetIeqPreset = 17;
        static final int TRANSACTION_iSetParameter = 15;
        static final int TRANSACTION_iSetProfile = 20;
        static final int TRANSACTION_iSetProfileName = 30;
        static final int TRANSACTION_iSetState = 9;
        static final int TRANSACTION_iUnregisterCallback = 8;
        static final int TRANSACTION_iUnregisterDeathHandler = 4;
        static final int TRANSACTION_iUnregisterDsAccess = 6;
        static final int TRANSACTION_iUnregisterVisualizerData = 2;

        private static class Proxy implements IDs {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "tripndroid.dolby.audio.api.IDs";
            }

            public void iRegisterVisualizerData(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void iUnregisterVisualizerData(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void iRegisterDeathHandler(int handle, IDsDeathHandler dh) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    if (dh != null) {
                        iBinder = dh.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void iUnregisterDeathHandler(int handle, IDsDeathHandler dh) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    if (dh != null) {
                        iBinder = dh.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void iRegisterDsAccess(int handle, DsClientInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void iUnregisterDsAccess(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void iRegisterCallback(int handle, IDsCallbacks cb, int version) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    if (cb != null) {
                        iBinder = cb.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    _data.writeInt(version);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void iUnregisterCallback(int handle, IDsCallbacks cb, int version) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    if (cb != null) {
                        iBinder = cb.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    _data.writeInt(version);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iSetState(int handle, int Device, boolean on) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    _data.writeInt(Device);
                    if (on) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetState(int Device, int[] on) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(Device);
                    if (on == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(on.length);
                    }
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(on);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetOffType(int[] offType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    if (offType == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(offType.length);
                    }
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(offType);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetDsServiceVersion(String[] version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    if (version == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(version.length);
                    }
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readStringArray(version);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetDapLibraryVersion(String[] version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    if (version == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(version.length);
                    }
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readStringArray(version);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetUdcLibraryVersion(String[] version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    if (version == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(version.length);
                    }
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readStringArray(version);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iSetParameter(int handle, int device, int profile, int paramId, int[] values) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    _data.writeInt(device);
                    _data.writeInt(profile);
                    _data.writeInt(paramId);
                    _data.writeIntArray(values);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetParameter(int device, int profile, int paramId, int[] values) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(device);
                    _data.writeInt(profile);
                    _data.writeInt(paramId);
                    if (values == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(values.length);
                    }
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(values);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iSetIeqPreset(int handle, int device, int preset) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    _data.writeInt(device);
                    _data.writeInt(preset);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetIeqPreset(int device, int[] preset) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(device);
                    if (preset == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(preset.length);
                    }
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(preset);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetIeqPresetCount(int device, int[] count) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(device);
                    if (count == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(count.length);
                    }
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(count);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iSetProfile(int handle, int device, int profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    _data.writeInt(device);
                    _data.writeInt(profile);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetProfile(int device, int[] profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(device);
                    if (profile == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(profile.length);
                    }
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(profile);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iResetProfile(int handle, int device, int profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    _data.writeInt(device);
                    _data.writeInt(profile);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetProfileModified(int device, int profile, boolean[] flag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(device);
                    _data.writeInt(profile);
                    if (flag == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(flag.length);
                    }
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readBooleanArray(flag);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetProfileCount(int device, int[] count) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(device);
                    if (count == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(count.length);
                    }
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(count);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iRequestAccessRight(int handle, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    _data.writeInt(type);
                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iAbandonAccessRight(int handle, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    _data.writeInt(type);
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iCheckAccessRight(int handle, int type, int[] state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    _data.writeInt(type);
                    if (state == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(state.length);
                    }
                    this.mRemote.transact(27, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(state);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetParamLength(int paramId, int[] len) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(paramId);
                    if (len == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(len.length);
                    }
                    this.mRemote.transact(28, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(len);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetMonoSpeaker(boolean[] mono) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    if (mono == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(mono.length);
                    }
                    this.mRemote.transact(29, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readBooleanArray(mono);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iSetProfileName(int handle, int profile, DsProfileName name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    _data.writeInt(profile);
                    if (name != null) {
                        _data.writeInt(1);
                        name.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(30, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int iGetProfileName(int handle, int profile, DsProfileName[] name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeInt(handle);
                    _data.writeInt(profile);
                    if (name == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(name.length);
                    }
                    this.mRemote.transact(31, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readTypedArray(name, DsProfileName.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] iGetProfileParameter(String profileType, String parameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeString(profileType);
                    _data.writeString(parameter);
                    this.mRemote.transact(32, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] iGetProfileEndpointParameter(String profileType, String endpoint, String parameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeString(profileType);
                    _data.writeString(endpoint);
                    _data.writeString(parameter);
                    this.mRemote.transact(33, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] iGetProfilePortParameter(String profileType, String port, String parameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeString(profileType);
                    _data.writeString(port);
                    _data.writeString(parameter);
                    this.mRemote.transact(34, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] iGetTuningParameter(String port, String parameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDs");
                    _data.writeString(port);
                    _data.writeString(parameter);
                    this.mRemote.transact(35, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "tripndroid.dolby.audio.api.IDs");
        }

        public static IDs asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("tripndroid.dolby.audio.api.IDs");
            if (iin == null || !(iin instanceof IDs)) {
                return new Proxy(obj);
            }
            return (IDs) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _arg0;
            int _result;
            int _arg1_length;
            int[] iArr;
            int _arg0_length;
            String[] strArr;
            int _arg1;
            int _arg2_length;
            int[] _result2;
            switch (code) {
                case 1:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    iRegisterVisualizerData(data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    iUnregisterVisualizerData(data.readInt());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    iRegisterDeathHandler(data.readInt(), tripndroid.dolby.audio.api.IDsDeathHandler.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    iUnregisterDeathHandler(data.readInt(), tripndroid.dolby.audio.api.IDsDeathHandler.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 5:
                    DsClientInfo dsClientInfo;
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    if (data.readInt() != 0) {
                        dsClientInfo = (DsClientInfo) DsClientInfo.CREATOR.createFromParcel(data);
                    } else {
                        dsClientInfo = null;
                    }
                    iRegisterDsAccess(_arg0, dsClientInfo);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    iUnregisterDsAccess(data.readInt());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    iRegisterCallback(data.readInt(), tripndroid.dolby.audio.api.IDsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    iUnregisterCallback(data.readInt(), tripndroid.dolby.audio.api.IDsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result = iSetState(data.readInt(), data.readInt(), data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 10:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1_length = data.readInt();
                    if (_arg1_length < 0) {
                        iArr = null;
                    } else {
                        iArr = new int[_arg1_length];
                    }
                    _result = iGetState(_arg0, iArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeIntArray(iArr);
                    return true;
                case 11:
                    int[] iArr2;
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0_length = data.readInt();
                    if (_arg0_length < 0) {
                        iArr2 = null;
                    } else {
                        iArr2 = new int[_arg0_length];
                    }
                    _result = iGetOffType(iArr2);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeIntArray(iArr2);
                    return true;
                case 12:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0_length = data.readInt();
                    if (_arg0_length < 0) {
                        strArr = null;
                    } else {
                        strArr = new String[_arg0_length];
                    }
                    _result = iGetDsServiceVersion(strArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeStringArray(strArr);
                    return true;
                case 13:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0_length = data.readInt();
                    if (_arg0_length < 0) {
                        strArr = null;
                    } else {
                        strArr = new String[_arg0_length];
                    }
                    _result = iGetDapLibraryVersion(strArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeStringArray(strArr);
                    return true;
                case 14:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0_length = data.readInt();
                    if (_arg0_length < 0) {
                        strArr = null;
                    } else {
                        strArr = new String[_arg0_length];
                    }
                    _result = iGetUdcLibraryVersion(strArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeStringArray(strArr);
                    return true;
                case 15:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result = iSetParameter(data.readInt(), data.readInt(), data.readInt(), data.readInt(), data.createIntArray());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 16:
                    int[] iArr3;
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    int _arg3_length = data.readInt();
                    if (_arg3_length < 0) {
                        iArr3 = null;
                    } else {
                        iArr3 = new int[_arg3_length];
                    }
                    _result = iGetParameter(_arg0, _arg1, _arg2, iArr3);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeIntArray(iArr3);
                    return true;
                case 17:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result = iSetIeqPreset(data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 18:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1_length = data.readInt();
                    if (_arg1_length < 0) {
                        iArr = null;
                    } else {
                        iArr = new int[_arg1_length];
                    }
                    _result = iGetIeqPreset(_arg0, iArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeIntArray(iArr);
                    return true;
                case 19:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1_length = data.readInt();
                    if (_arg1_length < 0) {
                        iArr = null;
                    } else {
                        iArr = new int[_arg1_length];
                    }
                    _result = iGetIeqPresetCount(_arg0, iArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeIntArray(iArr);
                    return true;
                case 20:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result = iSetProfile(data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 21:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1_length = data.readInt();
                    if (_arg1_length < 0) {
                        iArr = null;
                    } else {
                        iArr = new int[_arg1_length];
                    }
                    _result = iGetProfile(_arg0, iArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeIntArray(iArr);
                    return true;
                case 22:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result = iResetProfile(data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 23:
                    boolean[] zArr;
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    _arg2_length = data.readInt();
                    if (_arg2_length < 0) {
                        zArr = null;
                    } else {
                        zArr = new boolean[_arg2_length];
                    }
                    _result = iGetProfileModified(_arg0, _arg1, zArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeBooleanArray(zArr);
                    return true;
                case 24:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1_length = data.readInt();
                    if (_arg1_length < 0) {
                        iArr = null;
                    } else {
                        iArr = new int[_arg1_length];
                    }
                    _result = iGetProfileCount(_arg0, iArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeIntArray(iArr);
                    return true;
                case 25:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result = iRequestAccessRight(data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 26:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result = iAbandonAccessRight(data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 27:
                    int[] iArr4;
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    _arg2_length = data.readInt();
                    if (_arg2_length < 0) {
                        iArr4 = null;
                    } else {
                        iArr4 = new int[_arg2_length];
                    }
                    _result = iCheckAccessRight(_arg0, _arg1, iArr4);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeIntArray(iArr4);
                    return true;
                case 28:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1_length = data.readInt();
                    if (_arg1_length < 0) {
                        iArr = null;
                    } else {
                        iArr = new int[_arg1_length];
                    }
                    _result = iGetParamLength(_arg0, iArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeIntArray(iArr);
                    return true;
                case 29:
                    boolean[] zArr2;
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0_length = data.readInt();
                    if (_arg0_length < 0) {
                        zArr2 = null;
                    } else {
                        zArr2 = new boolean[_arg0_length];
                    }
                    _result = iGetMonoSpeaker(zArr2);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeBooleanArray(zArr2);
                    return true;
                case 30:
                    DsProfileName dsProfileName;
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if (data.readInt() != 0) {
                        dsProfileName = (DsProfileName) DsProfileName.CREATOR.createFromParcel(data);
                    } else {
                        dsProfileName = null;
                    }
                    _result = iSetProfileName(_arg0, _arg1, dsProfileName);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 31:
                    DsProfileName[] dsProfileNameArr;
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    _arg2_length = data.readInt();
                    if (_arg2_length < 0) {
                        dsProfileNameArr = null;
                    } else {
                        dsProfileNameArr = new DsProfileName[_arg2_length];
                    }
                    _result = iGetProfileName(_arg0, _arg1, dsProfileNameArr);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    reply.writeTypedArray(dsProfileNameArr, 1);
                    return true;
                case 32:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result2 = iGetProfileParameter(data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeIntArray(_result2);
                    return true;
                case 33:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result2 = iGetProfileEndpointParameter(data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeIntArray(_result2);
                    return true;
                case 34:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result2 = iGetProfilePortParameter(data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeIntArray(_result2);
                    return true;
                case 35:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDs");
                    _result2 = iGetTuningParameter(data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeIntArray(_result2);
                    return true;
                case 1598968902:
                    reply.writeString("tripndroid.dolby.audio.api.IDs");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    int iAbandonAccessRight(int i, int i2) throws RemoteException;

    int iCheckAccessRight(int i, int i2, int[] iArr) throws RemoteException;

    int iGetDapLibraryVersion(String[] strArr) throws RemoteException;

    int iGetDsServiceVersion(String[] strArr) throws RemoteException;

    int iGetIeqPreset(int i, int[] iArr) throws RemoteException;

    int iGetIeqPresetCount(int i, int[] iArr) throws RemoteException;

    int iGetMonoSpeaker(boolean[] zArr) throws RemoteException;

    int iGetOffType(int[] iArr) throws RemoteException;

    int iGetParamLength(int i, int[] iArr) throws RemoteException;

    int iGetParameter(int i, int i2, int i3, int[] iArr) throws RemoteException;

    int iGetProfile(int i, int[] iArr) throws RemoteException;

    int iGetProfileCount(int i, int[] iArr) throws RemoteException;

    int[] iGetProfileEndpointParameter(String str, String str2, String str3) throws RemoteException;

    int iGetProfileModified(int i, int i2, boolean[] zArr) throws RemoteException;

    int iGetProfileName(int i, int i2, DsProfileName[] dsProfileNameArr) throws RemoteException;

    int[] iGetProfileParameter(String str, String str2) throws RemoteException;

    int[] iGetProfilePortParameter(String str, String str2, String str3) throws RemoteException;

    int iGetState(int i, int[] iArr) throws RemoteException;

    int[] iGetTuningParameter(String str, String str2) throws RemoteException;

    int iGetUdcLibraryVersion(String[] strArr) throws RemoteException;

    void iRegisterCallback(int i, IDsCallbacks iDsCallbacks, int i2) throws RemoteException;

    void iRegisterDeathHandler(int i, IDsDeathHandler iDsDeathHandler) throws RemoteException;

    void iRegisterDsAccess(int i, DsClientInfo dsClientInfo) throws RemoteException;

    void iRegisterVisualizerData(int i) throws RemoteException;

    int iRequestAccessRight(int i, int i2) throws RemoteException;

    int iResetProfile(int i, int i2, int i3) throws RemoteException;

    int iSetIeqPreset(int i, int i2, int i3) throws RemoteException;

    int iSetParameter(int i, int i2, int i3, int i4, int[] iArr) throws RemoteException;

    int iSetProfile(int i, int i2, int i3) throws RemoteException;

    int iSetProfileName(int i, int i2, DsProfileName dsProfileName) throws RemoteException;

    int iSetState(int i, int i2, boolean z) throws RemoteException;

    void iUnregisterCallback(int i, IDsCallbacks iDsCallbacks, int i2) throws RemoteException;

    void iUnregisterDeathHandler(int i, IDsDeathHandler iDsDeathHandler) throws RemoteException;

    void iUnregisterDsAccess(int i) throws RemoteException;

    void iUnregisterVisualizerData(int i) throws RemoteException;
}
