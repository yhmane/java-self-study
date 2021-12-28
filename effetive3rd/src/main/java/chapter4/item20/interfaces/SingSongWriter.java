package chapter4.item20.interfaces;

import java.applet.AudioClip;

public interface SingSongWriter extends Singer, SongWriter {

    AudioClip strum();
    void actSensitive();
}
