package com.spworm.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class Exhibition implements DomainObject {

	private Long id;
	private String exhibitionName;
	private Set<ArtEntity> exhibitionArtWork = new HashSet<ArtEntity>();

	private Integer version;

	public Exhibition() {
	}

	@Id
	@GeneratedValue
	public final Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExhibitionName() {
		return exhibitionName;
	}

	public void setExhibitionName(String exhibitionName) {
		this.exhibitionName = exhibitionName;
	}

	@ManyToMany
	public Set<ArtEntity> getExhibitionArtWork() {
		return exhibitionArtWork;
	}

	public void setExhibitionArtWork(Set<ArtEntity> exhibitionArtWork) {
		this.exhibitionArtWork = exhibitionArtWork;
	}

	@Version
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * This method adds an artEntity to an exhibition It is a unidirectional
	 * relationship
	 * 
	 * @param art
	 * @return success
	 */
	public boolean addArtToExhibition(ArtEntity art) {
		return this.getExhibitionArtWork().add(art);
	}

}
