package com.spworm.dao;

import org.springframework.dao.DataAccessException;

import com.spworm.model.domain.ArtData;
import com.spworm.model.domain.ArtEntity;

public interface ArtDataDao extends GenericDao<ArtData> {

    // gets (loading proxy) appropriate image format from artEntity (such as thumbnail, etc.)
    public ArtData getArtDataFromEntity(ArtEntity entity, String imageFormat) throws DataAccessException;
}
