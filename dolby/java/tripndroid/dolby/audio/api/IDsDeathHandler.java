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

public interface IDsDeathHandler extends IInterface {

    public static abstract class Stub extends Binder implements IDsDeathHandler {
        private static final String DESCRIPTOR = "tripndroid.dolby.audio.api.IDsDeathHandler";
        static final int TRANSACTION_onClientDied = 1;

        private static class Proxy implements IDsDeathHandler {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "tripndroid.dolby.audio.api.IDsDeathHandler";
            }

            public void onClientDied() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("tripndroid.dolby.audio.api.IDsDeathHandler");
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "tripndroid.dolby.audio.api.IDsDeathHandler");
        }

        public static IDsDeathHandler asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("tripndroid.dolby.audio.api.IDsDeathHandler");
            if (iin == null || !(iin instanceof IDsDeathHandler)) {
                return new Proxy(obj);
            }
            return (IDsDeathHandler) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("tripndroid.dolby.audio.api.IDsDeathHandler");
                    onClientDied();
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("tripndroid.dolby.audio.api.IDsDeathHandler");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onClientDied() throws RemoteException;
}
