package com.example.blogex.domain.hashtag.service;


import com.example.blogex.domain.hashtag.dto.HashtagDto;
import com.example.blogex.domain.hashtag.dto.HashtagRankDto;
import com.example.blogex.domain.hashtag.entitiy.HashTag;
import com.example.blogex.domain.hashtag.repository.HashtagRepository;
import com.example.blogex.domain.posttag.repository.PostTagRepository;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class HashtagService {
    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;
    private final PostTagRepository postTagRepository;
    
    //해쉬 태그 생성
    public HashtagDto createHashtag(String tag) {
        HashTag hashtag = hashtagRepository.findByTag(tag);

        if (hashtag == null) {
            hashtag = hashtagRepository.save(
                    HashTag.builder().tag(tag).build()
            );
        }

        return HashtagDto.builder()
                .id(hashtag.getId())
                .tag(hashtag.getTag())
                .build();
    }

    //해쉬태그 순위 출력
    public List<HashtagRankDto> getHashTagRank(){
        List<HashTag> tags=hashtagRepository.findPopularTags();

        return tags.stream().map(h->{
            return HashtagRankDto.builder()
                    .id(h.getId())
                    .tag(h.getTag())
                    .cnt(h.getPosts().size()).build();
        }).toList();
    }

    //특정 유저가 많이 사용한 해쉬태그 보여주기
    public List<HashtagRankDto> getUserHashtagRank(Long userID){
        List<HashTag> tags=hashtagRepository.findPopularTagsByUserId(userID);

        return tags.stream().map(t->{
            return HashtagRankDto.builder()
                    .id(t.getId())
                    .tag(t.getTag())
                    .cnt(postTagRepository.countByUserAndHashtag(userID,t))
                    .build();
        }).toList();

    }




}
