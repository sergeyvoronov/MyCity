package com.yalantis.internship.requestapplication;

//ImageModel Class for urls
class ImageModel { //[Comment] You don't need this. Use List of strings
  private final String mUrl;

  public ImageModel(String url) {
    this.mUrl = url;
  }

  public String getUrl() {
    return mUrl;
  }
}
