package com.spworm.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spworm.model.domain.ArtEntity;

public interface ArtEntityDao extends GenericDao<ArtEntity> {

    public ArtEntity getArtEntityByTitle(String title) throws DataAccessException;

    public List<ArtEntity> getArtInExhibition(Long exhibitionId) throws DataAccessException;

    public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException;

    //public List<ArtEntity> searchForArtEntitiesByTerms(String searchTerms, Integer startIndex, Integer maxResults);
}
