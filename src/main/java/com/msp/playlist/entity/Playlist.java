package com.msp.playlist.entity;

import com.msp.playlist.dto.PlaylistRequestDto;
import com.msp.playlist.dto.PlaylistUpdateDto;
import com.msp.song.entity.Song;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// GETTER SETTER 다 없애줘라 ~ LOMBOK
@Entity
@Getter //member 변수들이 전부 private 밖으로 가져오기 위해
@Table(name="playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name_playlist")
    private String name;

    @Column(name="description_playlist")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "userid")
    private Long userId;

    @OneToMany(mappedBy = "playlist")
    private List<Song> songs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private TagGenre tagGenre;

/*    @ManyToMany
    @JoinTable(
            name = "playlist_mood",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "mood_id")
    )
    private Set<TagMood> tagMoodSet = new HashSet<>();*/

    public Playlist(PlaylistRequestDto playlistRequestDto){
        this.name = playlistRequestDto.getName();
        this.description = playlistRequestDto.getDescription();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.userId = playlistRequestDto.getUserID();
    }

    public Playlist() {

    }

    public void changeNameAndDescription(PlaylistUpdateDto playlistUpdateDto) {
        if (playlistUpdateDto.getName() != null) {
            this.name = playlistUpdateDto.getName();
        }
        if (playlistUpdateDto.getDescription() != null) {
            this.description = playlistUpdateDto.getDescription();
        }
        this.updatedAt = LocalDateTime.now();
    }

}
