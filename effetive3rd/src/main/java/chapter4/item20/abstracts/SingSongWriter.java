package chapter4.item20.abstracts;

import java.applet.AudioClip;

import chapter4.item20.Song;

public abstract class SingSongWriter {

    abstract AudioClip sing(Song s);
    abstract Song compose(int charPosition);
    abstract AudioClip strum();
    abstract void actSensitive();
}
