sap.ui.define([
    "sap/fe/test/JourneyRunner",
	"jvtrantxt/test/integration/pages/MediaFilesList.gen",
	"jvtrantxt/test/integration/pages/MediaFilesObjectPage.gen"
], function (JourneyRunner, MediaFilesListGenerated, MediaFilesObjectPageGenerated) {
    'use strict';

    const runner = new JourneyRunner({
        launchUrl: sap.ui.require.toUrl('jvtrantxt') + '/test/flpSandbox.html#jvtrantxt-tile',
        pages: {
			onTheMediaFilesListGenerated: MediaFilesListGenerated,
			onTheMediaFilesObjectPageGenerated: MediaFilesObjectPageGenerated
        },
        async: true
    });

    return runner;
});

