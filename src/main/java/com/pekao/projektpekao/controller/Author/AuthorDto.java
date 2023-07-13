package com.pekao.projektpekao.controller.Author;

public class AuthorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String authorPhotoUrl;

    public Long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public String getAuthorPhotoUrl() {
        return authorPhotoUrl;
    }

    public static final class Builder {
        private Long id;

        private String firstName;
        private String lastName;
        private String authorPhotoUrl;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder authorPhotoUrl(String authorPhotoUrl) {
            this.authorPhotoUrl = authorPhotoUrl;
            return this;
        }

        public AuthorDto build() {
            AuthorDto authorDto = new AuthorDto();
            authorDto.id = this.id;
            authorDto.firstName = this.firstName;
            authorDto.lastName = this.lastName;
            authorDto.authorPhotoUrl = this.authorPhotoUrl;
            return authorDto;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
