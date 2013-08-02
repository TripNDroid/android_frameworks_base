#
# Stars Audio Package
#
# Include this file in a product makefile to include these audio files
#
#

LOCAL_PATH:= frameworks/base/data/sounds

# Effects
PRODUCT_COPY_FILES += \
	$(LOCAL_PATH)/effects/ogg/camera_click.ogg:system/media/audio/ui/camera_click.ogg \
	$(LOCAL_PATH)/effects/ogg/camera_focus.ogg:system/media/audio/ui/camera_focus.ogg \
	$(LOCAL_PATH)/effects/ogg/Dock.ogg:system/media/audio/ui/Dock.ogg \
	$(LOCAL_PATH)/effects/ogg/Effect_Tick.ogg:system/media/audio/ui/Effect_Tick.ogg \
	$(LOCAL_PATH)/effects/ogg/KeypressDelete.ogg:system/media/audio/ui/KeypressDelete.ogg \
	$(LOCAL_PATH)/effects/ogg/KeypressReturn.ogg:system/media/audio/ui/KeypressReturn.ogg \
	$(LOCAL_PATH)/effects/ogg/KeypressSpacebar.ogg:system/media/audio/ui/KeypressSpacebar.ogg \
	$(LOCAL_PATH)/effects/ogg/KeypressStandard.ogg:system/media/audio/ui/KeypressStandard.ogg \
	$(LOCAL_PATH)/effects/ogg/Lock.ogg:system/media/audio/ui/Lock.ogg \
	$(LOCAL_PATH)/effects/ogg/LowBattery.ogg:system/media/audio/ui/LowBattery.ogg \
	$(LOCAL_PATH)/effects/ogg/Media_Volume.ogg:system/media/audio/ui/Media_Volume.ogg \
	$(LOCAL_PATH)/effects/ogg/Undock.ogg:system/media/audio/ui/Undock.ogg \
	$(LOCAL_PATH)/effects/ogg/Unlock.ogg:system/media/audio/ui/Unlock.ogg \
	$(LOCAL_PATH)/effects/ogg/VideoRecord.ogg:system/media/audio/ui/VideoRecord.ogg \
	$(LOCAL_PATH)/effects/ogg/VideoStop.ogg:system/media/audio/ui/VideoStop.ogg \
	$(LOCAL_PATH)/effects/ogg/VolumeIncremental.ogg:system/media/audio/ui/VolumeIncremental.ogg

# Notifications
PRODUCT_COPY_FILES += \
	$(LOCAL_PATH)/notifications/ogg/Aldebaran.ogg:system/media/audio/notifications/Aldebaran.ogg \
	$(LOCAL_PATH)/notifications/ogg/Antares.ogg:system/media/audio/notifications/Antares.ogg \
	$(LOCAL_PATH)/notifications/ogg/Canopus.ogg:system/media/audio/notifications/Canopus.ogg \
	$(LOCAL_PATH)/notifications/ogg/Capella.ogg:system/media/audio/notifications/Capella.ogg \
	$(LOCAL_PATH)/notifications/ogg/Electra.ogg:system/media/audio/notifications/Electra.ogg \
	$(LOCAL_PATH)/notifications/ogg/Merope.ogg:system/media/audio/notifications/Merope.ogg \
	$(LOCAL_PATH)/notifications/ogg/Mira.ogg:system/media/audio/notifications/Mira.ogg \
	$(LOCAL_PATH)/notifications/ogg/Polaris.ogg:system/media/audio/notifications/Polaris.ogg \
	$(LOCAL_PATH)/notifications/ogg/Pollux.ogg:system/media/audio/notifications/Pollux.ogg \
	$(LOCAL_PATH)/notifications/ogg/Procyon.ogg:system/media/audio/notifications/Procyon.ogg \
	$(LOCAL_PATH)/notifications/ogg/Proxima.ogg:system/media/audio/notifications/Proxima.ogg \
	$(LOCAL_PATH)/notifications/ogg/Regulus.ogg:system/media/audio/notifications/Regulus.ogg \
	$(LOCAL_PATH)/notifications/ogg/Sirrah.ogg:system/media/audio/notifications/Sirrah.ogg \
	$(LOCAL_PATH)/notifications/ogg/Upsilon.ogg:system/media/audio/notifications/Upsilon.ogg

# Ringtones
PRODUCT_COPY_FILES += \
	$(LOCAL_PATH)/ringtones/ogg/Acheron.ogg:system/media/audio/ringtones/Acheron.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Andromeda.ogg:system/media/audio/ringtones/Andromeda.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Aquila.ogg:system/media/audio/ringtones/Aquila.ogg \
	$(LOCAL_PATH)/ringtones/ogg/ArgoNavis.ogg:system/media/audio/ringtones/ArgoNavis.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Bootes.ogg:system/media/audio/ringtones/Bootes.ogg \
	$(LOCAL_PATH)/ringtones/ogg/CanisMajor.ogg:system/media/audio/ringtones/CanisMajor.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Carina.ogg:system/media/audio/ringtones/Carina.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Cygnus.ogg:system/media/audio/ringtones/Cygnus.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Eridani.ogg:system/media/audio/ringtones/Eridani.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Hydra.ogg:system/media/audio/ringtones/Hydra.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Iridium.ogg:system/media/audio/ringtones/Iridium.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Nasqueron.ogg:system/media/audio/ringtones/Nasqueron.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Orion.ogg:system/media/audio/ringtones/Orion.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Pegasus.ogg:system/media/audio/ringtones/Pegasus.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Perseus.ogg:system/media/audio/ringtones/Perseus.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Pyxis.ogg:system/media/audio/ringtones/Pyxis.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Rigel.ogg:system/media/audio/ringtones/Rigel.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Solarium.ogg:system/media/audio/ringtones/Solarium.ogg \
	$(LOCAL_PATH)/ringtones/ogg/Zeta.ogg:system/media/audio/ringtones/Zeta.ogg
