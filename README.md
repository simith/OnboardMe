# OnboardMe
A Mobile User Onboarding flow for android Apps

# Using the code

Bulk of the code for startup and init is under OnboardingPagerActivity.java
```
mPagerMgr = new PageViewManager();
mPagerMgr.init();
mPagerMgr.setTakeoffActivity(TakeoffActivity.class);

//Change the onbaording slide images.
//TODO : Make this a string array and pass it in the Intent for this activity bundle.
Page p = new Page(0, "#354353", R.drawable.onboard);
mPagerMgr.addPage(p);
p = new Page(1, "#354ABC", R.drawable.two);
mPagerMgr.addPage(p);
p = new Page(2, "#23f353", R.drawable.onboard3);
mPagerMgr.addPage(p);
```

# TODO

- [ ] Animate slide changes with fade in and fade out tranformation as seen in the Google Docs app
- [ ] Provide an API for setting different animation types to the Page Manager. 
- [ ] Make complete views passed as input to the PageManager instead of just images
- [ ] Make the Page indicator change color based on the dominant color of the Page/Slide
- [ ] Make the list of images for slides a string array and pass it in the Intent for the OnboardingPagerActivity activity bundle.
- [ ] Make the source code a Library instead of an Android Project
- [ ] Refactor code to make the API easy to configure and use in Apps

