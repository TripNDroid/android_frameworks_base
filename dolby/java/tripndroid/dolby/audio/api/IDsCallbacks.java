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

public interface IDsCallbacks extends IInterface {

    public static abstract class Stub extends Binder implements IDsCallbacks {
        private static final String DESCRIPTOR = "tripndroid.dolby.audio.api.IDsCallbacks";
        static final int TRANSACTION_onAccessAvailable = 8;
        static final int TRANSACTION_onAccessForceReleased = 7;
        static final int TRANSACTION_onAccessRequested = 9;
        static final int TRANSACTION_onDsOn = 1;
        static final int TRANSACTION_onDsSuspended = 2;
        static final int TRANSACTION_onLegacyClientSetting = 11;
        static final int TRANSACTION_onProfileNameChanged = 10;
        static final int TRANSACTION_onProfileSelected = 3;
        static final int TRANSACTION_onProfileSettingsChanged = 4;
        static final int TRANSACTION_onVisualizerSuspended = 6;
        static final int TRANSACTION_onVisualizerUpdated = 5;

        private static class Proxy implements IDsCallbacks {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "tripndroid.dolby.audio.api.IDsCallbacks";
            }

            public void onDsOn(boolean on) throws RemoteException {
                int i = 1;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDsCallbacks");
                    if (!on) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onDsSuspended(boolean isSuspended) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDsCallbacks");
                    if (isSuspended) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onProfileSelected(int profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDsCallbacks");
                    _data.writeInt(profile);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onProfileSettingsChanged(int profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDsCallbacks");
                    _data.writeInt(profile);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onVisualizerUpdated(float[] gains, float[] excitations) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDsCallbacks");
                    _data.writeFloatArray(gains);
                    _data.writeFloatArray(excitations);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onVisualizerSuspended(boolean isSuspended) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDsCallbacks");
                    if (isSuspended) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onAccessForceReleased(String app, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDsCallbacks");
                    _data.writeString(app);
                    _data.writeInt(type);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onAccessAvailable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDsCallbacks");
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean onAccessRequested(String app, int type) throws RemoteException {
                boolean _result = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(app);
                    obtain.writeInt(type);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        _result = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
             return _result;
            }

            public void onProfileNameChanged(int profile, String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDsCallbacks");
                    _data.writeInt(profile);
                    _data.writeString(name);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean onLegacyClientSetting() throws RemoteException {
                boolean _result = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        _result = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
             return _result;
            }
        }

        public Stub() {
            attachInterface(this, "tripndroid.dolby.audio.api.IDsCallbacks");
        }

        public static IDsCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("tripndroid.dolby.audio.api.IDsCallbacks");
            if (iin == null || !(iin instanceof IDsCallbacks)) {
                return new Proxy(obj);
            }
            return (IDsCallbacks) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _arg0 = 0;
            boolean _arg02 = false;
            boolean _result;
            switch (code) {
                case 1:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    if (data.readInt() != 0) {
                        _arg02 = true;
                    }
                    onDsOn(_arg02);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    if (data.readInt() != 0) {
                        _arg02 = true;
                    }
                    onDsSuspended(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    onProfileSelected(data.readInt());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    onProfileSettingsChanged(data.readInt());
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    onVisualizerUpdated(data.createFloatArray(), data.createFloatArray());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    if (data.readInt() != 0) {
                        _arg02 = true;
                    }
                    onVisualizerSuspended(_arg02);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    onAccessForceReleased(data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    onAccessAvailable();
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    _result = onAccessRequested(data.readString(), data.readInt());
                    reply.writeNoException();
                    if (_result) {
                        _arg0 = 1;
                    }
                    reply.writeInt(_arg0);
                    return true;
                case 10:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    onProfileNameChanged(data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsCallbacks");
                    _result = onLegacyClientSetting();
                    reply.writeNoException();
                    if (_result) {
                        _arg0 = 1;
                    }
                    reply.writeInt(_arg0);
                    return true;
                case 1598968902:
                    reply.writeString("tripndroid.dolby.audio.api.IDsCallbacks");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onAccessAvailable() throws RemoteException;

    void onAccessForceReleased(String str, int i) throws RemoteException;

    boolean onAccessRequested(String str, int i) throws RemoteException;

    void onDsOn(boolean z) throws RemoteException;

    void onDsSuspended(boolean z) throws RemoteException;

    boolean onLegacyClientSetting() throws RemoteException;

    void onProfileNameChanged(int i, String str) throws RemoteException;

    void onProfileSelected(int i) throws RemoteException;

    void onProfileSettingsChanged(int i) throws RemoteException;

    void onVisualizerSuspended(boolean z) throws RemoteException;

    void onVisualizerUpdated(float[] fArr, float[] fArr2) throws RemoteException;
}
