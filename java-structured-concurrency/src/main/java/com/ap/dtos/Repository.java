package com.ap.dtos;

import java.net.URI;

public record Repository(String username, Visibility visibility, URI uri) {
}
