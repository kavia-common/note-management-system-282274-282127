package com.example.notesbackend.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Exposes a simple redirect for /docs to the Swagger UI page.
 * This avoids 404s for static /docs and preserves the request's external scheme/host via X-Forwarded headers.
 */
@RestController
@Tag(name = "Documentation", description = "API documentation redirect")
public class DocsController {

    // PUBLIC_INTERFACE
    @GetMapping("/docs")
    /** Redirects to Swagger UI, preserving host/scheme for reverse proxies. */
    public RedirectView docs(HttpServletRequest request) {
        String target = UriComponentsBuilder
                .fromHttpRequest(new ServletServerHttpRequest(request))
                .replacePath("/swagger-ui.html")
                .replaceQuery(null)
                .build()
                .toUriString();
        RedirectView rv = new RedirectView(target);
        rv.setHttp10Compatible(false);
        return rv;
    }
}
