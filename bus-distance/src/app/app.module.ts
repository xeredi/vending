import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpModule, Http, XHRBackend, RequestOptions } from '@angular/http';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home.component';

import { SharedModule } from './shared/shared.module';
import { MaestroModule } from './maestro/maestro.module';

@NgModule( {
    declarations: [
        AppComponent
        , HomeComponent
    ]
    , imports: [
        BrowserModule
        , FormsModule
        , HttpModule
        , NgbModule.forRoot()

        , AppRoutingModule
        , SharedModule
        , MaestroModule
    ]
    , providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy }
    ]
    , bootstrap: [AppComponent]
    , exports: [
        BrowserModule
        , HttpModule
    ]
} )
export class AppModule { }
