/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.template.soy.tofu.internal;

import com.google.template.soy.data.SoyRecord;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.SoyValueHelper;
import com.google.template.soy.shared.restricted.SoyJavaFunction;
import com.google.template.soy.sharedpasses.render.EvalVisitor;
import com.google.template.soy.sharedpasses.render.EvalVisitor.EvalVisitorFactory;
import com.google.template.soy.tofu.internal.TofuModule.Tofu;

import java.util.Deque;
import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Implementation of EvalVisitorFactory for Tofu backend.
 *
 */
@Singleton
class TofuEvalVisitorFactory implements EvalVisitorFactory {


  /** Instance of SoyValueHelper to use. */
  private final SoyValueHelper valueHelper;

  /** Map of all SoyJavaFunctions (name to function). */
  private final Map<String, SoyJavaFunction> soyJavaFunctionsMap;


  @Inject
  public TofuEvalVisitorFactory(
      SoyValueHelper valueHelper, @Tofu Map<String, SoyJavaFunction> soyJavaFunctionsMap) {
    this.valueHelper = valueHelper;
    this.soyJavaFunctionsMap = soyJavaFunctionsMap;
  }


  @Override public EvalVisitor create(
      SoyRecord data, @Nullable SoyRecord ijData, Deque<Map<String, SoyValue>> env) {

    return new TofuEvalVisitor(valueHelper, soyJavaFunctionsMap, data, ijData, env);
  }

}
