import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { RutaService } from './ruta.service';

@Component( {
    selector: 'app-ruta-edit',
    templateUrl: './ruta-edit.component.html'
} )
export class RutaEditComponent implements OnInit {
    model: any;
    action: string;

    constructor(
        private location: Location
        , private route: ActivatedRoute
        , private router: Router
        , private modelService: RutaService
    ) {
    }

    ngOnInit() {
        this.route.params.subscribe( params => {
            this.action = params['action'];

            this.modelService.edit( this.action, { id: +params['id'] } )
                .subscribe( resp => {
                    this.model = resp.model;
                } );
        } );
    }

    save() {
        this.modelService.save( this.action, this.model )
            .subscribe( resp => {
                this.action == 'edit' ? this.location.back()
                    : this.router.navigate( ['/maestro/ruta/detail', resp.model.id], { replaceUrl: true } );
            } );
    }

    cancel() {
        this.location.back();
    }
}

