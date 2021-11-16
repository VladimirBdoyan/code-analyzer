package com.company.statisticsservice.Service.gitUser;


import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.mapper.*;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.repository.GitUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GitUserServiceImpl implements GitUserService {

    private final GitUserRepository gitUserRepository;


    public GitUserServiceImpl(GitUserRepository gitUserRepository) {
        this.gitUserRepository = gitUserRepository;
    }


//    @Override
//    @Transactional
//    public Optional<GitAccessResponseDto> create(GitAccessResponseDto dto) {
//
//        //TODO fix code style Optional object don't get in save method
//        Optional<GitUser> gitUser = GitUserDtoMapper.mapToEntity(dto);
//        CommitDensity commitDensity = CommitDensityMapper.mapToEntity(dto).get();
//        GitRepository repository = GitRepositoryMapper.mapToEntity(dto).get();
//
//        gitUser = Optional.of(gitUserRepository.save(gitUser.get()));
//        repository.setGitUser(gitUser.get());
//        repository = gitHubRepository.save(repository);
//        commitDensity.setUserName(gitUser.get());
//        commitDensity.setRepoName(repository);
//        commitDensity = commitDensityRepository.save(commitDensity);
//
//        Optional<GitAccessResponseDto> dtoResponse;
//        dtoResponse = CommitDensityMapper.mapToDto(commitDensity);
//
//
//        return dtoResponse;
//    }
    @Override
    @Transactional
    public GitUser createUser(GitAccessResponseDto dto, GitOrganization organization) {
        GitUser gitUser = GitUserDtoMapper.mapToEntity(dto).get();
        gitUser.setGitOrganization(organization);
        gitUser = gitUserRepository.save(gitUser);
        return gitUser;
    }

    @Override
    @Transactional
    public GitUser isPresent(String login) {
        GitUser gitUser ;
        try {
            gitUser = gitUserRepository.findFirstByLogin(login);
        } catch (NullPointerException e) {
            return null;
        }
        return gitUser;
    }
}
