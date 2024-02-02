package com.legend.library.enums;

public enum Genre {
    FANTASY("Fantasy"),
    SCIENCE_FICTION("Science Fiction"),
    DYSTOPIAN("Dystopian"),
    ACTION_AND_ADVENTURE("Action & Adventure"),
    DETECTIVE_AND_MYSTERY("Detective & Mystery"),
    THRILLER_AND_SUSPENSE("Thriller & Suspense"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    HISTORICAL_FICTION("Historical Fiction"),
    YOUNG_ADULT("Young Adult"),
    CHILDRENS_FICTION("Children’s Fiction"),
    WOMENS_FICTION("Women’s Fiction"),
    CONTEMPORARY_FICTION("Contemporary Fiction"),
    LITERARY_FICTION("Literary Fiction"),
    GRAPHIC_NOVELS("Graphic Novels"),
    SHORT_STORY("Short Story"),
    MEMOIR_AND_AUTOBIOGRAPHY("Memoir & Autobiography"),
    BIOGRAPHY("Biography"),
    HISTORY("History"),
    FOOD_AND_DRINK("Food & Drink"),
    POETRY("Poetry"),
    SELF_HELP("Self-Help"),
    TRUE_CRIME("True Crime"),
    TRAVEL("Travel"),
    ART_AND_PHOTOGRAPHY("Art & Photography"),
    ESSAYS("Essays"),
    HUMANITIES_AND_SOCIAL_SCIENCES("Humanities & Social Sciences"),
    SCIENCE_AND_TECHNOLOGY("Science & Technology");

    private final String label;

    Genre(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
