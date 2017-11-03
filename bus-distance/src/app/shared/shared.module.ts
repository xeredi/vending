import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { MaestroModule } from '../maestro/maestro.module';

import { CrudService } from './crud.service';

@NgModule( {
    imports: [
        CommonModule
        , FormsModule
        , NgbModule
    ]
    , declarations: [
    ]
    , providers: [
    ]
    , exports: [
        CommonModule
        , FormsModule
        , NgbModule
    ]
} )
export class SharedModule { }
