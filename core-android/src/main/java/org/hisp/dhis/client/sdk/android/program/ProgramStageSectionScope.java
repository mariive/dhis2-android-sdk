/*
 * Copyright (c) 2016, University of Oslo
 *
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.hisp.dhis.client.sdk.android.program;


import org.hisp.dhis.client.sdk.core.program.IProgramStageSectionController;
import org.hisp.dhis.client.sdk.core.program.IProgramStageSectionService;
import org.hisp.dhis.client.sdk.models.program.ProgramStage;
import org.hisp.dhis.client.sdk.models.program.ProgramStageSection;
import org.hisp.dhis.client.sdk.models.utils.ModelUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rx.Observable;
import rx.Subscriber;

public class ProgramStageSectionScope implements IProgramStageSectionScope {
    private final IProgramStageSectionController programStageSectionController;
    private final IProgramStageSectionService programStageSectionService;

    public ProgramStageSectionScope(IProgramStageSectionController programStageSectionController,
                                    IProgramStageSectionService programStageSectionService) {
        this.programStageSectionController = programStageSectionController;
        this.programStageSectionService = programStageSectionService;
    }

    @Override
    public Observable<List<ProgramStageSection>> sync() {
        return Observable.create(new Observable.OnSubscribe<List<ProgramStageSection>>() {

            @Override
            public void call(Subscriber<? super List<ProgramStageSection>> subscriber) {
                try {
                    programStageSectionController.sync();
                    subscriber.onNext(programStageSectionService.list());
                } catch (Throwable throwable) {
                    subscriber.onError(throwable);
                }

                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<List<ProgramStageSection>> sync(final String... uids) {
        return Observable.create(new Observable.OnSubscribe<List<ProgramStageSection>>() {

            @Override
            public void call(Subscriber<? super List<ProgramStageSection>> subscriber) {
                try {
                    Set<String> uidSet = new HashSet<>(ModelUtils.asList(uids));
                    programStageSectionController.sync(uidSet);
                    subscriber.onNext(programStageSectionService.list(uidSet));
                } catch (Throwable throwable) {
                    subscriber.onError(throwable);
                }

                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<ProgramStageSection> get(final String uid) {
        return Observable.create(new Observable.OnSubscribe<ProgramStageSection>() {

            @Override
            public void call(Subscriber<? super ProgramStageSection> subscriber) {
                try {
                    ProgramStageSection programStageSection = programStageSectionService.get(uid);
                    subscriber.onNext(programStageSection);
                } catch (Throwable throwable) {
                    subscriber.onError(throwable);
                }

                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<ProgramStageSection> get(final long id) {
        return Observable.create(new Observable.OnSubscribe<ProgramStageSection>() {

            @Override
            public void call(Subscriber<? super ProgramStageSection> subscriber) {
                try {
                    ProgramStageSection programStageSection = programStageSectionService.get(id);
                    subscriber.onNext(programStageSection);
                } catch (Throwable throwable) {
                    subscriber.onError(throwable);
                }

                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<List<ProgramStageSection>> list() {
        return Observable.create(new Observable.OnSubscribe<List<ProgramStageSection>>() {

            @Override
            public void call(Subscriber<? super List<ProgramStageSection>> subscriber) {
                try {
                    List<ProgramStageSection> programStageSections =
                            programStageSectionService.list();
                    subscriber.onNext(programStageSections);
                } catch (Throwable throwable) {
                    subscriber.onError(throwable);
                }

                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<List<ProgramStageSection>> list(final ProgramStage programStage) {
        return Observable.create(new Observable.OnSubscribe<List<ProgramStageSection>>() {

            @Override
            public void call(Subscriber<? super List<ProgramStageSection>> subscriber) {
                try {
                    List<ProgramStageSection> programStageSections =
                            programStageSectionService.list(programStage);
                    subscriber.onNext(programStageSections);
                } catch (Throwable throwable) {
                    subscriber.onError(throwable);
                }

                subscriber.onCompleted();
            }
        });
    }
}
