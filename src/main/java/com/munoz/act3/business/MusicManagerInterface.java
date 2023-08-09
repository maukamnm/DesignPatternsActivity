package com.munoz.act3.business;

import com.munoz.act3.beans.*;
import com.munoz.act3.util.TracksNotFoundException;

public interface MusicManagerInterface {
    Album addAlbum(Album model) throws TracksNotFoundException;
}
