/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import com.krismorte.simplerepository.identity.IdentityAndAudit;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author c007329
 */
@MappedSuperclass
public class User extends IdentityAndAudit {

    @Column(length = 40)
    private String profileName;
    @Column(length = 150)
    private String email;
    @Column(length = 400)
    private String profileImage;
    @Column(length = 400)
    private String profileImageUrl;
    private String description;
    private LocalDate createAt;
    private String location;
    private Long profileId;
    private Long totalFriends;
    private Long totalFollowers;
    private Long totalStatuses;

    public User() {
    }

    /**
     * @return the profileName
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * @param profileName the profileName to set
     */
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the createAt
     */
    public LocalDate getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt the createAt to set
     */
    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the profileId
     */
    public Long getProfileId() {
        return profileId;
    }

    /**
     * @param profileId the profileId to set
     */
    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    /**
     * @return the totalFriends
     */
    public Long getTotalFriends() {
        return totalFriends;
    }

    /**
     * @param totalFriends the totalFriends to set
     */
    public void setTotalFriends(Long totalFriends) {
        this.totalFriends = totalFriends;
    }

    /**
     * @return the totalFollowers
     */
    public Long getTotalFollowers() {
        return totalFollowers;
    }

    /**
     * @param totalFollowers the totalFollowers to set
     */
    public void setTotalFollowers(Long totalFollowers) {
        this.totalFollowers = totalFollowers;
    }

    /**
     * @return the totalStatuses
     */
    public Long getTotalStatuses() {
        return totalStatuses;
    }

    /**
     * @param totalStatuses the totalStatuses to set
     */
    public void setTotalStatuses(Long totalStatuses) {
        this.totalStatuses = totalStatuses;
    }

    /**
     * @return the profileImage
     */
    public String getProfileImage() {
        return profileImage;
    }

    /**
     * @param profileImage the profileImage to set
     */
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * @return the profileImageUrl
     */
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    /**
     * @param profileImageUrl the profileImageUrl to set
     */
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public String toString() {
        return getProfileName();
    }

}
