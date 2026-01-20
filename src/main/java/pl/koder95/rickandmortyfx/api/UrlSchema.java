package pl.koder95.rickandmortyfx.api;

record UrlSchema(String endpointUrl) {
    public String idUrl(Long id) {
        return endpointUrl + "/" + id;
    }

    public String pageUrl(Integer page) {
        return page == null || page.compareTo(1) < 0
                ? firstPageUrl() : endpointUrl + "?page=" + page;
    }

    private String firstPageUrl() {
        return endpointUrl;
    }
}
